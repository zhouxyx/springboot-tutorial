package com.xxx.springboot.mybatis.service;

import com.xxx.springboot.mybatis.entity.User;

public interface UserService {

	public User queryUserById(Long userId);
}
