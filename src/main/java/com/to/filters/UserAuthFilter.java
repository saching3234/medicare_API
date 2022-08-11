package com.to.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import com.to.Constants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class UserAuthFilter extends GenericFilterBean{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// converting the generic servlet request and response into http servlet request
		// and response
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		// getting the bearer string
		String authHeader = httpServletRequest.getHeader("Authorization");

		// checking the header is null or not
		if (authHeader != null) {
			// split the string into the array
			String[] authHeaderArr = authHeader.split("Bearer");

			// checking the token
			if (authHeaderArr.length > 1 && authHeaderArr[1] != null) {
				String token = authHeaderArr[1];
				try {
					// checking the token and setting the user id in the http request
					Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY_STRING).parseClaimsJws(token)
							.getBody();
					//setting the UserId to httpServletRequest for accessing the user ID later
					httpServletRequest.setAttribute("userId",Integer.parseInt(claims.get("userId").toString()));
					httpServletRequest.setAttribute("userName",claims.get("name").toString());
					System.out.println("User filter :User Id : " + claims.get("userId").toString());
					System.out.println("User filter :User Name : " + claims.get("name").toString());

					System.out.println(httpServletRequest.getAttribute("userId"));
				} catch (Exception e) {
					httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(),
							"Invalid / Expired user token reason :" + e.getMessage());
					return;
				}

			} else {
				httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(),
						"Authorization token must be Bearer in user token");
				return;
			}

		} else {
			httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(),
					"Authorization token must be provide for user");
			return;
		}
  
		
		//If all is set then continue the process
		chain.doFilter(request, response);
		
		
	}
	

}
