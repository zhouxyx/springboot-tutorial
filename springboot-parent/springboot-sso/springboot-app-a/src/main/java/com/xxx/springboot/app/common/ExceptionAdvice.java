package com.xxx.springboot.app.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {

	
	@ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String,String> errorHandler(Exception ex) {
		Map<String,String> map = new HashMap<String,String>();
		if(ex instanceof UnauthorizedException) {
			map.put("code", "100");
	        map.put("msg", "没有权限");
		}else {
			map.put("code", "101");
	        map.put("msg", "其他异常");
		}
        return map;
    }
}
