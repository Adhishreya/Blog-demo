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

	public String getCloud_name() {
		return cloud_name;
	}

	public void setCloud_name(String cloud_name) {
		this.cloud_name = cloud_name;
	}

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public String getApi_secret() {
		return api_secret;
	}

	public void setApi_secret(String api_secret) {
		this.api_secret = api_secret;
	}

	public String getCloudinary_url() {
		return cloudinary_url;
	}

	public void setCloudinary_url(String cloudinary_url) {
		this.cloudinary_url = cloudinary_url;
	}

	@Value("${api_secret}")
	private String api_secret;

	@Value("${cloudinary_url}")
	private String cloudinary_url;
}
