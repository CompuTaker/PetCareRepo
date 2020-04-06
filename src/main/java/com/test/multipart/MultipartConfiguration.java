package com.test.multipart;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class MultipartConfiguration extends WebMvcConfigurerAdapter{
	
	private final int MAX_SIZE = 10 * 1024 * 1024;
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    resolver.setMaxUploadSize(this.MAX_SIZE); // 10MB
	    resolver.setMaxUploadSizePerFile(this.MAX_SIZE); // 10MB
	    resolver.setMaxInMemorySize(0);
	    
	    return resolver;
	}
	
}
