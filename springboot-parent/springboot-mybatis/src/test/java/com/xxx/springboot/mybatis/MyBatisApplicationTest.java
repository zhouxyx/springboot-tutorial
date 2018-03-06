package com.xxx.springboot.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xxx.springboot.mybatis.entity.User;
import com.xxx.springboot.mybatis.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisApplicationTest{ 
   
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private UserMapper userMapper;
	
	@Test
	public void queryUserByID() {
		User user = userService.queryUserById(123456L);
		System.out.println(user);
	}
}
