package com.xxx.springboot.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xxx.springboot.mybatis.mapper")
public class MyBatisApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyBatisApplication.class, args);
	}
}
