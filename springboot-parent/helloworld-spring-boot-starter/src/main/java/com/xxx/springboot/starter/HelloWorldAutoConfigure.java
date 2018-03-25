package com.xxx.springboot.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(HelloWorldService.class)
public class HelloWorldAutoConfigure {

	@Bean
	@ConditionalOnMissingBean
	HelloWorldService helloWorldService() {
		return new HelloWorldService();
	}
}
