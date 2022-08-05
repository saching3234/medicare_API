package com.to;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


import com.to.filters.AdminAuthFilter;
import com.to.filters.UserAuthFilter;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class MedicareApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicareApiApplication.class, args);	
		
	}
	
	
	//registering the admin filter bean
	@Bean
	public FilterRegistrationBean<AdminAuthFilter> adminFilterRegistrationBean(){
		FilterRegistrationBean<AdminAuthFilter> registrationBean=new FilterRegistrationBean<>();
		AdminAuthFilter adminAuthFilter=new AdminAuthFilter();
		registrationBean.setFilter(adminAuthFilter);
		//set the url for scanning  the request
		registrationBean.addUrlPatterns("/api/admin/category/*","/api/admin/products/*");
		return registrationBean;
	}
	
	
	//registering the user filter bean
	@Bean
	public FilterRegistrationBean<UserAuthFilter> userFilterRegistrationBean(){
		//creating the object of FilterRegistrationBean. 
		FilterRegistrationBean<UserAuthFilter> userRegistrationBean=new FilterRegistrationBean<>();
		//creating the object of UserAuthFilter class.
		UserAuthFilter userAuthFilter=new UserAuthFilter();
		//setting the filter
		userRegistrationBean.setFilter(userAuthFilter);
		//setting the urls for scanning the request
		userRegistrationBean.addUrlPatterns("/api/users/change/*","/api/users/orders/*","/api/users/category/*","/api/users/products/*","/api/users/cart/*");
		return userRegistrationBean;
	}

}
