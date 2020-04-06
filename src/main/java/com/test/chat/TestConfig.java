package com.test.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	
	@Bean
	Car car() {
		return new Car();
	}
	
}
