package com.xxx.springboot.shiro.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.xxx.springboot.shiro.model.Resource;
import com.xxx.springboot.shiro.model.Role;
import com.xxx.springboot.shiro.model.User;
import com.xxx.springboot.shiro.service.UserService;

public class MyShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		User userInfo = (User) principals.getPrimaryPrincipal();

		for (Role role : userService.findRolesByUserId(userInfo.getUserId())) {
			authorizationInfo.addRole(role.getRoleName());

			for (Resource Resource : userService.findResourcesByRoleId(role.getRoleId())) {
				authorizationInfo.addStringPermission(Resource.getPermissionKey());
			}
		}
		return authorizationInfo;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
		// 获取用户的输入的账号.
		String username = (String) token.getPrincipal();
		System.out.println(token.getCredentials());
		// 通过username从数据库中查找 User对象，如果找到，没找到.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		User userInfo = userService.findByUsername(username);
		System.out.println("----->>userInfo=" + userInfo);
		if (userInfo == null) {
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, // 用户名
				userInfo.getPassword(), // 密码
				// ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}

}