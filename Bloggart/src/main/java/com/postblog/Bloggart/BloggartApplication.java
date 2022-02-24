package com.postblog.Bloggart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication
public class BloggartApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggartApplication.class, args);
	}

}
