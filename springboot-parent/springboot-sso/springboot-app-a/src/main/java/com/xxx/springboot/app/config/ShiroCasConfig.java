package com.xxx.springboot.app.config;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;

import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@Configuration
@SuppressWarnings("deprecation")
public class ShiroCasConfig {

	@Value("${shiro.cas.serverUrlPrefix}")
	private String casServerUrlPrefix ;
	
	@Value("${shiro.cas.service}")
	private String casService ;
	
	@Value("${shiro.failureUrl}")
	private String failureUrl;
	@Value("${shiro.successUrl}")
	private String successUrl;
	
	
	@Value("${shiro.loginUrl}")
	private String loginUrl;
	
	@Value("${shiro.logoutUrl}")
	private String redirectUrl;
	
	@Bean
	public CasSubjectFactory casSubjectFactory() {
		return new CasSubjectFactory();
	}
	
	@Bean
	public CasFilter casFilter() {
		CasFilter casFilter = new CasFilter();
		casFilter.setFailureUrl(failureUrl);
		casFilter.setSuccessUrl(successUrl);
		return casFilter;
	}
	
	@Bean
	public LogoutFilter logoutFilter() {
		LogoutFilter filter = new LogoutFilter();
		filter.setRedirectUrl(redirectUrl);
		return filter;
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		shiroFilterFactoryBean.setLoginUrl(loginUrl);
		
		Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
		filters.put("casFilter", casFilter());
		filters.put("logoutFilter", logoutFilter());
		shiroFilterFactoryBean.setFilters(filters);
		
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		filterChainDefinitionMap.put("/shiro-cas", "casFilter");
		filterChainDefinitionMap.put("/logout", "logoutFilter");
		filterChainDefinitionMap.put("/users/**", "authc");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return shiroFilterFactoryBean;
	}
	@Bean
	public FilterRegistrationBean delegatingFilterProxy(){
	    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	    DelegatingFilterProxy proxy = new DelegatingFilterProxy();
	    proxy.setTargetFilterLifecycle(true);
	    proxy.setTargetBeanName("shiroFilter");
	    filterRegistrationBean.setFilter(proxy);
	    return filterRegistrationBean;
	}
	
	@Bean
	public UserRealm casRealm(){
		UserRealm real = new UserRealm();
		real.setCasServerUrlPrefix(casServerUrlPrefix);
		real.setCasService(casService);
		return real;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
	
	
	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(casRealm());
		securityManager.setSubjectFactory(casSubjectFactory());
		return securityManager;
	}
	
	@Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }
	
	@Bean(name="simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver
	createSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
		mappings.setProperty("UnauthorizedException","403");
		r.setExceptionMappings(mappings);  // None by default
		r.setDefaultErrorView("error");    // No default
		r.setExceptionAttribute("ex");     // Default is "exception"
		return r;
	}
	
	
}
