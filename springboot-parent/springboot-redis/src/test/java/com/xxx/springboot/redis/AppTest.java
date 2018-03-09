package com.xxx.springboot.redis;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xxx.springboot.redis.op.RedisOp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest  {
	
	@Autowired
	private RedisOp redisOp;
	
	
	@Test
	public void set() {
		redisOp.set("key", "value");
	}
	
	@Test
	public void expire() {
		
		redisOp.expire("key", 20L );
	}
}
