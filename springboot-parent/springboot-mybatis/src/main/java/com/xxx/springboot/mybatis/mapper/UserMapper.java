package com.xxx.springboot.mybatis.mapper;

import com.xxx.springboot.mybatis.entity.User;

public interface UserMapper {

	public User queryUserById(Long userId);
}
