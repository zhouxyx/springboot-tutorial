package com.xxx.rbac.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.rbac.common.IdWorker;
import com.xxx.rbac.common.Pagination;
import com.xxx.rbac.common.PaginationVo;
import com.xxx.rbac.model.User;
import com.xxx.rbac.service.UserService;
import com.xxx.rbac.vo.UserVo;

@Service
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

	public Pagination<UserVo> queryForPagination(UserVo userVo, PaginationVo paginationVo) {
		StringBuilder sql = new StringBuilder("select user_id , user_name , phone from sys_user where 1=1 ");
		StringBuilder contion = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		if (null != userVo.getPhone()) {
			contion.append("phone like %?% ");
			params.add(userVo.getPhone());
		}
		if (null != userVo.getUserName()) {
			contion.append("user_name like %?% ");
			params.add(userVo.getUserName());
		}

		String countSql = "select count(1) from sys_user where 1=1 " + contion.toString();

		int totals = jdbcTemplate.queryForObject(countSql, params.toArray(), Integer.class);
		int pages = (totals - 1) / paginationVo.getPageSizes() + 1;
		int pageSizes = paginationVo.getPageSizes();
		int page = paginationVo.getPage();
		Pagination<UserVo> pagination = new Pagination<UserVo>();
		pagination.setPages(pages);
		pagination.setPageSizes(pageSizes);
		pagination.setTotals(totals);
		pagination.setPage(page);

		int startRow = (-1) * pageSizes;
		sql.append(" limit ?,?");
		params.add(startRow);
		params.add(pageSizes);

		List<UserVo> userList = jdbcTemplate.query(sql.toString(), params.toArray(), new RowMapper<UserVo>() {
			public UserVo mapRow(ResultSet rs, int index) throws SQLException {
				UserVo user = new UserVo();
				user.setUserId(rs.getLong("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPhone(rs.getString("phone"));
				return user;
			}
		});
		pagination.setDatas(userList);
		return pagination;
	}

	@Transactional
	public boolean updatePassword(Long userId, String password) {
		String update = "update sys_user set password = ? where user_id = ?";
		int num = jdbcTemplate.update(update, userId, password);
		return num > 0 ? true : false;
	}

}
