package com.xxx.rbac.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "back")
public class MainController {

	@GetMapping(value = "index")
	public String index() {

		return "index";
	}

}
