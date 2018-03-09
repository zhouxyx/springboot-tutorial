package com.xxx.springboot.redis.op;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisOp {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	
	public boolean exist(String key) {
		return redisTemplate.hasKey(key);
	}
	
	public void set(String key, String value) {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		opsForValue.set(key, value);
	}
	
	public String get(String key) {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		return opsForValue.get(key);
	}
	/**
	 * 
	 * @param key
	 * @param timeout 秒
	 * @return
	 */
	public boolean expire(String key , Long timeout) {
		return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}
	
	
	
}
