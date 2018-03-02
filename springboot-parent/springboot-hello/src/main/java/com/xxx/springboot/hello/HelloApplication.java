package com.xxx.springboot.hello;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * springboot入门教程
 */
@RestController
@SpringBootApplication
public class HelloApplication {

	@GetMapping(value = "hello")
	public Map<String, String> hello() {
		Map<String, String> map = new HashMap<String, String>(10);
		map.put("message", "Hello Spring Boot!");
		return map;
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}
}
