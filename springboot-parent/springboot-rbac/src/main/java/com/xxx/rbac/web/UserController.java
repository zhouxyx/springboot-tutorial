package com.xxx.rbac.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xxx.rbac.common.Pagination;
import com.xxx.rbac.common.PaginationVo;
import com.xxx.rbac.model.User;
import com.xxx.rbac.service.UserService;
import com.xxx.rbac.vo.ResultVo;
import com.xxx.rbac.vo.UserVo;

@RestController
@RequestMapping(value = "sys")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	@GetMapping(value = "session/set")
	public String session(HttpSession session) {
		session.setAttribute("KEY", "VALUE");
		return "SET SESSION";
	}

	@GetMapping(value = "session/get")
	public String getSession(HttpSession session) {
		return "A:"+session.getAttribute("KEY").toString();
	}
	
	
	@PostMapping(value = "user")
	public ResultVo save(@RequestBody User user) {
		ResultVo rv = new ResultVo();
		Boolean isSucc = userService.saveUser(user);
		if (isSucc) {
			rv.setStatus("1000");
			rv.setMessage("用户信息保存成功");
			rv.setType("succ");
		} else {
			rv.setStatus("9999");
			rv.setMessage("用户信息保存失败");
			rv.setType("error");
		}
		return rv;
	}

	@GetMapping(value = "user/{userId}")
	public UserVo get(@PathVariable Long userId) {
		User user = userService.queryUserById(userId);
		UserVo userVo = new UserVo();
		userVo.setPhone(user.getPhone());
		userVo.setUserId(user.getUserId());
		userVo.setUserName(user.getUserName());
		return userVo;
	}
	
	@GetMapping(value="users")
	public Pagination<UserVo> list(UserVo userVo, PaginationVo paginationVo){
		return userService.queryForPagination(userVo, paginationVo);
	}
	
	@PutMapping(value="user/password")
	public ResultVo updatePassword( Long userId, String password) {
		boolean isSucc = userService.updatePassword(userId, password);
		ResultVo rv = new ResultVo();
		if (isSucc) {
			rv.setStatus("1000");
			rv.setMessage("密码修改成功");
			rv.setType("succ");
		} else {
			rv.setStatus("9999");
			rv.setMessage("密码修改失败");
			rv.setType("error");
		}
		return rv;
	}
	
	
}
