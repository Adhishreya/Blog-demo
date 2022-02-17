package com.postblog.Bloggart.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:credentials.properties")
public class ReadProp {
@Value("${cloud_name}")
private String cloud_name;

@Value("${api_key}")
private String api_key;


@Value("${api_secret}")
private String api_secret;

@Value("${cloudinary_url}")
private String cloudinary_url;
}
