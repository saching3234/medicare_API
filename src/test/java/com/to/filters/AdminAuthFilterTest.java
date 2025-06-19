package com.to.filters;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

class AdminAuthFilterTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    FilterChain chain;

    @InjectMocks
    AdminAuthFilter filter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        filter = new AdminAuthFilter();
    }

    private String generateToken() {
        return Jwts.builder()
                .claim("adminId", 1)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 100000))
                .signWith(SignatureAlgorithm.HS256, com.to.Constants.API_SECRET_KEY_STRING)
                .compact();
    }

    @Test
    void testDoFilter_validToken() throws Exception {
        String token = generateToken();
        when(request.getHeader("Authorization")).thenReturn("Bearer" + token);
        when(request.getMethod()).thenReturn("GET");

        filter.doFilter(request, response, chain);

        verify(chain, times(1)).doFilter(request, response);
        verify(response, never()).sendError(anyInt(), anyString());
    }

    @Test
    void testDoFilter_missingAuthorizationHeader() throws Exception {
        when(request.getHeader("Authorization")).thenReturn(null);
        when(request.getMethod()).thenReturn("GET");

        filter.doFilter(request, response, chain);

        verify(response).sendError(eq(403), contains("Authorization token must be provide for admin"));
        verify(chain, never()).doFilter(any(), any());
    }

    @Test
    void testDoFilter_invalidToken() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("Bearer invalidtoken");
        when(request.getMethod()).thenReturn("GET");

        filter.doFilter(request, response, chain);

        verify(response).sendError(eq(403), contains("Invalid / Expired admin token"));
        verify(chain, never()).doFilter(any(), any());
    }

    @Test
    void testDoFilter_optionsRequest() throws Exception {
        when(request.getMethod()).thenReturn("OPTIONS");

        filter.doFilter(request, response, chain);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(chain, never()).doFilter(any(), any());
    }

    @Test
    void testDoFilter_authorizationHeaderWithoutBearerToken() throws Exception {
        when(request.getHeader("Authorization")).thenReturn("SomeOtherValue");
        when(request.getMethod()).thenReturn("GET");

        filter.doFilter(request, response, chain);

        verify(response).sendError(eq(403), contains("Authorization token must be Bearer in admin token"));
        verify(chain, never()).doFilter(any(), any());
    }
}
