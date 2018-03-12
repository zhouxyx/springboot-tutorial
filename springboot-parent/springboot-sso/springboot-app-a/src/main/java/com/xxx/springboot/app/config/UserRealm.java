package com.xxx.springboot.app.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.xxx.springboot.app.model.Resource;
import com.xxx.springboot.app.model.Role;
import com.xxx.springboot.app.model.User;
import com.xxx.springboot.app.service.UserService;

/**
 * 用户授权信息域
 * 
 * @author coderhuang
 * 
 */
public class UserRealm extends CasRealm {
	
	@Autowired
	private UserService userService;

	protected final Map<String, SimpleAuthorizationInfo> roles = new ConcurrentHashMap<String, SimpleAuthorizationInfo>();
	
	/**
	 * 设置角色和权限信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		String username =(String) principals.getPrimaryPrincipal();
		
		User user = userService.findByUsername(username);
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		for (Role role : userService.findRolesByUserId(user.getUserId())) {
			authorizationInfo.addRole(role.getRoleName());
			for (Resource Resource : userService.findResourcesByRoleId(role.getRoleId())) {
				authorizationInfo.addStringPermission(Resource.getPermissionKey());
			}
		}
		
		roles.put(user.getUserName(), authorizationInfo);
		
		return authorizationInfo;
	}
	
	
	/**
	 * 1、CAS认证 ,验证用户身份
	 * 2、将用户基本信息设置到会话中
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {

		AuthenticationInfo authc = super.doGetAuthenticationInfo(token);
		String username = (String) token.getPrincipal();
		User user = userService.findByUsername(username);
		SecurityUtils.getSubject().getSession().setAttribute("user", user);

		return authc;
	}
	

}