package com.xxx.springboot.dubbo.api;

import com.xxx.springboot.dubbo.pojo.User;

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
	
	
	/**
	 * 更新密码
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean updatePassword(Long userId, String password);
}
