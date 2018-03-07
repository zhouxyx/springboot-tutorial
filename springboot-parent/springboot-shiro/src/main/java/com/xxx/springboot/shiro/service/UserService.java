package com.xxx.springboot.shiro.service;

import java.util.List;

import com.xxx.springboot.shiro.model.Resource;
import com.xxx.springboot.shiro.model.Role;
import com.xxx.springboot.shiro.model.User;

public interface UserService {

	User findByUsername(String username);

	List<Role> findRolesByUserId(Long userId);
	
	List<Resource> findResourcesByRoleId(Long roleId);
}
