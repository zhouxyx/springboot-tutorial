package com.xxx.springboot.shiro.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "back/user")
public class UserController {

	@RequestMapping(value = "add")
	@RequiresPermissions(value = { "user:add" })
	public String add() {

		return "user_add";
	}

	@RequiresPermissions(value = { "user:del" })
	@RequestMapping(value = "del")
	public String del() {
		return "user_del";
	}

	@RequiresPermissions(value = { "user:update" })
	@RequestMapping(value = "update")
	public String update() {

		return "user_update";
	}

	@RequiresPermissions(value = { "user:query" })
	@RequestMapping(value = "query")
	public String query() {
		return "user_query";
	}
}
