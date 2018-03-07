package com.xxx.springboot.dubbo.provider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.xxx.springboot.dubbo.api.UserService;
import com.xxx.springboot.dubbo.pojo.IdWorker;
import com.xxx.springboot.dubbo.pojo.User;

@Service(interfaceClass = UserService.class)
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User queryUserById(Long userId) {

		String sql = "select user_id , user_name , phone from sys_user where user_id = ?";

		List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
			public User mapRow(ResultSet rs, int index) throws SQLException {
				User user = new User();
				user.setUserId(rs.getLong("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPhone(rs.getString("phone"));
				return user;
			}

		}, userId);
		User user = DataAccessUtils.singleResult(userList);
		return user;
	}

	@Transactional
	public boolean saveUser(User user) {
		user.setUserId(IdWorker.getFlowIdWorkerInstance().nextId());
		String insert = "insert into sys_user(user_id,user_name,password,phone) values(?,?,?,?)";
		int num = jdbcTemplate.update(insert, user.getUserId(), user.getUserName(), user.getPassword(),
				user.getPhone());
		return num > 0 ? true : false;
	}

	@Transactional
	public boolean updatePassword(Long userId, String password) {
		String update = "update sys_user set password = ? where user_id = ?";
		int num = jdbcTemplate.update(update, userId, password);
		return num > 0 ? true : false;
	}
}
