package com.xxx.springboot.dubbo.consumer.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xxx.springboot.dubbo.api.UserService;
import com.xxx.springboot.dubbo.consumer.vo.ResultVo;
import com.xxx.springboot.dubbo.consumer.vo.UserVo;
import com.xxx.springboot.dubbo.pojo.User;

@RestController
@RequestMapping(value = "api")
public class UserController {

	@Reference
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

	@PutMapping(value = "user/password")
	public ResultVo updatePassword(Long userId, String password) {
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
