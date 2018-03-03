package com.xxx.rbac.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xxx.rbac.model.User;
import com.xxx.rbac.service.UserService;
import com.xxx.rbac.vo.ResultVo;
import com.xxx.rbac.vo.UserVo;

@RestController
@RequestMapping(value = "sys")
public class UserController {

	@Autowired
	private UserService userService;

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
	
	

}
