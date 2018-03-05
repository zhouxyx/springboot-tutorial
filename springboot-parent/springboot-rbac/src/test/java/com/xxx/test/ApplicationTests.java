package com.xxx.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xxx.rbac.RbacApplication;
import com.xxx.rbac.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RbacApplication.class)
public class ApplicationTests {

	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	@Test
	public void test() throws Exception {

		ValueOperations<String, User> operations = redisTemplate.opsForValue();
		User user = new User();
		user.setUserId(123456L);
		user.setUserName("zhouxy");
		operations.set("user", user);
		User user2 = operations.get("user");
		System.out.println(user2);
	}
	

}