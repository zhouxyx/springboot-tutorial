package com.xxx.springboot.app.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class PermissionController {

	@GetMapping(value = "/user/add")
	@RequiresPermissions(value= {"user1:add1"})
	public String saveUser() {
		return "save-user";
	}
	
	@GetMapping(value = "/user/query")
	@RequiresPermissions(value= {"user:add"})
	public String queryUser() {
		return "query-user";
	}
}
