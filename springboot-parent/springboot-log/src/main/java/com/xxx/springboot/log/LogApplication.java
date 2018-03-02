package com.xxx.springboot.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class LogApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogApplication.class);
	
	@GetMapping(value="log")
	public String log() {
		//TRACE < DEBUG < INFO < WARN < ERROR
		LOGGER.trace("trace......");
		LOGGER.debug("debug......");
		LOGGER.info("info......");
		LOGGER.warn("warn......");
		LOGGER.error("error......");
		return "springboot log config";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LogApplication.class, args);
	}
}
