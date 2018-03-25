package com.xxx.springboot.app.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
// @ComponentScan(value= {"com.xxx.springboot.app.common.redis"})
public class ShiroConfig {

	private static final String casFilterUrlPattern = "/shiro-cas";

	@Bean
	public UserRealm getShiroRealm(@Value("${shiro.cas}") String casServerUrlPrefix,
			@Value("${shiro.server}") String shiroServerUrlPrefix) {
		UserRealm casRealm = new UserRealm();
		casRealm.setCasServerUrlPrefix(casServerUrlPrefix);
		casRealm.setCasService(shiroServerUrlPrefix + casFilterUrlPattern);
		return casRealm;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		filterRegistration.addInitParameter("targetFilterLifecycle", "true");
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");
		return filterRegistration;
	}

	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@ConditionalOnMissingBean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
		defaultAAP.setProxyTargetClass(true);
		return defaultAAP;
	}

	/**
	 * 开启注解支持
	 * 
	 * @param casServerUrlPrefix
	 * @param shiroServerUrlPrefix
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Value("${shiro.cas}") String casServerUrlPrefix, @Value("${shiro.server}") String shiroServerUrlPrefix,
			RedisTemplate<String, Object> redisTemplate) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(
				getDefaultWebSecurityManager(casServerUrlPrefix, shiroServerUrlPrefix, redisTemplate));
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public RedisCacheManager redisCacheManager() {
		return new RedisCacheManager();
	}

	@Bean
	public RedisSessionDAO sessionDao(RedisTemplate<String, Object> redisTemplate) {
		RedisSessionDAO sessionDao = new RedisSessionDAO();
		sessionDao.setRedisTemplate(redisTemplate);
		return sessionDao;
	}

	@Bean
	public SessionManager sessionManager(RedisTemplate<String, Object> redisTemplate) {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(sessionDao(redisTemplate));
		sessionManager.setGlobalSessionTimeout(1800);
		// sessionManager.setCacheManager(redisCacheManager());
		sessionManager.setSessionValidationSchedulerEnabled(true);
		return sessionManager;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Value("${shiro.cas}") String casServerUrlPrefix,
			@Value("${shiro.server}") String shiroServerUrlPrefix, RedisTemplate<String, Object> redisTemplate) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setCacheManager(redisCacheManager());
		securityManager.setSessionManager(sessionManager(redisTemplate));
		securityManager.setRealm(getShiroRealm(casServerUrlPrefix, shiroServerUrlPrefix));

		// securityManager.setCacheManager(new MemoryConstrainedCacheManager());
		securityManager.setSubjectFactory(new CasSubjectFactory());
		return securityManager;
	}

	/**
	 * 定义 CAS Filter
	 */
	@Bean(name = "casFilter")
	public CasFilter getCasFilter(@Value("${shiro.cas}") String casServerUrlPrefix,
			@Value("${shiro.server}") String shiroServerUrlPrefix) {
		CasFilter casFilter = new CasFilter();
		casFilter.setName("casFilter");
		casFilter.setEnabled(true);
		// 失败继续重定向单点登录界面
		String failUrl = casServerUrlPrefix + "/login?service=" + shiroServerUrlPrefix + casFilterUrlPattern;
		// 登录成功后重定向的地址
		String successUrl = shiroServerUrlPrefix + "/users/loginSuccess";
		casFilter.setFailureUrl(failUrl);
		casFilter.setSuccessUrl(successUrl);
		return casFilter;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager,
			CasFilter casFilter, @Value("${shiro.cas}") String casServerUrlPrefix,
			@Value("${shiro.server}") String shiroServerUrlPrefix) {

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		String loginUrl = casServerUrlPrefix + "/login?service=" + shiroServerUrlPrefix + casFilterUrlPattern;
		shiroFilterFactoryBean.setLoginUrl(loginUrl);
		shiroFilterFactoryBean.setSuccessUrl("/");
		Map<String, Filter> filters = new HashMap<>();
		filters.put("casFilter", casFilter);
		LogoutFilter logoutFilter = new LogoutFilter();
		String logoutUrl = casServerUrlPrefix + "/logout?service=" + shiroServerUrlPrefix + casFilterUrlPattern;
		logoutFilter.setRedirectUrl(logoutUrl);
		filters.put("logout", logoutFilter);
		shiroFilterFactoryBean.setFilters(filters);

		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put(casFilterUrlPattern, "casFilter");
		filterChainDefinitionMap.put("/jsp/index.jsp", "casFilter");
		filterChainDefinitionMap.put("/static", "anon");
		filterChainDefinitionMap.put("/logout", "logout");

		// filterChainDefinitionMap.put("/test/**", "authc");
		filterChainDefinitionMap.put("/users/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

}