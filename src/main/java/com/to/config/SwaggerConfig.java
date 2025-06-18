package com.to.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
 
/**
* @author Sachin Gawade
* @since May 08, 2024
* @version 2.0
*/
@Configuration
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "Medicare Application ", version = "2.0", description = "Medicare Application"))
public class SwaggerConfig {
 
@Bean
public	OpenAPI openApi() {
 
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("customauthtoken",
						new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER)
								.name("authorization")))
				.addSecurityItem(new SecurityRequirement().addList("customauthtoken"))
				.info(new Info().title("Medicare Application")
						.description("List of all the API for Medicare Application").version("v1.5")
						.contact(new Contact().name("Sachin Gawade")).termsOfService("TOC")
						.license(new License().name("License").url("#")));
	}
}
