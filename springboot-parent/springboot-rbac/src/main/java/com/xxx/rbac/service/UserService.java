package com.xxx.rbac.service;

import com.xxx.rbac.common.Pagination;
import com.xxx.rbac.common.PaginationVo;
import com.xxx.rbac.model.User;
import com.xxx.rbac.vo.UserVo;

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
	 * 查询用户列表
	 * @param userVo 
	 * @param paginationVo 页码条件
	 * @return
	 */
	public Pagination<UserVo> queryForPagination(UserVo userVo, PaginationVo paginationVo);
	
	/**
	 * 更新密码
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean updatePassword(Long userId, String password);
}
