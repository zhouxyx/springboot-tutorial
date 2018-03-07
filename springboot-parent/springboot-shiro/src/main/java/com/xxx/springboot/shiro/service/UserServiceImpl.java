package com.xxx.springboot.shiro.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.xxx.springboot.shiro.model.Resource;
import com.xxx.springboot.shiro.model.Role;
import com.xxx.springboot.shiro.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public User findByUsername(String username) {
		String sql = "select user_id , user_name ,password, phone from sys_user where user_name = ?";
		List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
			public User mapRow(ResultSet rs, int index) throws SQLException {
				User user = new User();
				user.setUserId(rs.getLong("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPhone(rs.getString("phone"));
				user.setPassword(rs.getString("password"));
				return user;
			}

		}, username);
		User user = DataAccessUtils.singleResult(userList);
		return user;
	}


	public List<Role> findRolesByUserId(Long userId) {
		String sql = "SELECT r.role_name,r.role_id FROM sys_role r , sys_user_role ur  WHERE ur.role_id = r.role_id AND r.product='ODC' AND ur.user_id = ?";
		List<Role> roleList = jdbcTemplate.query(sql, new RowMapper<Role>() {
			public Role mapRow(ResultSet rs, int index) throws SQLException {
				Role role = new Role();
				role.setRoleId(rs.getLong("role_id"));
				role.setRoleName(rs.getString("role_name"));
				return role;
			}
		}, userId);
		return roleList;
	}


	public List<Resource> findResourcesByRoleId(Long roleId) {
		String sql = "SELECT r.* FROM sys_resource r, sys_role_resource rr WHERE r.resource_id = rr.resource_id AND rr.role_id = ?";
		List<Resource> resourceList = jdbcTemplate.query(sql, new RowMapper<Resource>() {
			public Resource mapRow(ResultSet rs, int index) throws SQLException {
				Resource resource = new Resource();
				resource.setPermissionKey(rs.getString("permission_key"));
				return resource;
			}
		}, roleId);
		return resourceList;
	}

}
