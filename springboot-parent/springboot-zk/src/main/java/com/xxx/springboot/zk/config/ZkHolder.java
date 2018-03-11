package com.xxx.springboot.zk.config;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZkHolder {

	private final static String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
	
	@Bean
	public ZkClient zkClient() {
		ZkClient zkClient = new ZkClient(connectString, 4000);
		return zkClient;
	}
	
	
}
