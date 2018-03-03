package com.xxx.rbac.service;

import com.xxx.rbac.model.User;

public interface UserService {

	/**
	 * 通过ID查询用户
	 * @param userId
	 * @return User
	 */
	public User queryUserById(Long userId);
	
	/**
	 * 保存用户信息
	 * @param user
	 * @return 保存状态:true=成功,false=失败
	 */
	public boolean saveUser(User user);
}
