package com.xxx.springboot.app.service;

import java.util.List;

import com.xxx.springboot.app.model.Resource;
import com.xxx.springboot.app.model.Role;
import com.xxx.springboot.app.model.User;

public interface UserService {

	User findByUsername(String username);

	List<Role> findRolesByUserId(Long userId);
	
	List<Resource> findResourcesByRoleId(Long roleId);
}
