package com.to.config;

import static org.junit.jupiter.api.Assertions.*;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SwaggerConfigTest {

    @Test
    void openApiBeanConfigurationTest() {
        AnnotationConfigApplicationContext context = Mockito.spy(new AnnotationConfigApplicationContext(SwaggerConfig.class));
        OpenAPI openAPI = context.getBean(OpenAPI.class);
        assertNotNull(openAPI);
        assertNotNull(openAPI.getInfo());
        Info info = openAPI.getInfo();
        assertEquals("Medicare Application", info.getTitle());
        assertEquals("v1.5", info.getVersion());
        assertEquals("List of all the API for Medicare Application", info.getDescription());
        assertTrue(openAPI.getComponents().getSecuritySchemes().containsKey("customauthtoken"));
        SecurityScheme scheme = openAPI.getComponents().getSecuritySchemes().get("customauthtoken");
        assertEquals(SecurityScheme.Type.APIKEY, scheme.getType());
        assertEquals(SecurityScheme.In.HEADER, scheme.getIn());
        assertEquals("authorization", scheme.getName());
        context.close();
    }
}