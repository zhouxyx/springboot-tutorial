package com.xxx.springboot.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.springboot.mybatis.entity.User;
import com.xxx.springboot.mybatis.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	public User queryUserById(Long userId) {
		return userMapper.queryUserById(userId);
	}
}
