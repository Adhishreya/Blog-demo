//package com.postblog.Bloggart.configurations;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.postblog.Bloggart.dto.UserDto;
//
//@Configuration
//public class SessionConfigur {
//	@Bean
//	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
//	public UserDto getUser() {
//		return new UserDto();
//	}
//}
