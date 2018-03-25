package com.xxx.springboot.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootStarterApp {

	@Autowired
	private HelloWorldService helloWorldService;

	@GetMapping(value = "hello/{name}")
	public String hello(@PathVariable("name") String name) {
		return helloWorldService.wrap(name);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterApp.class, args);
	}
}
