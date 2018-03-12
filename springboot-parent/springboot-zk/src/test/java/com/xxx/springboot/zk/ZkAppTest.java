package com.xxx.springboot.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xxx.springboot.zk.op.ZkService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZkAppTest {

	@Autowired
	private ZkService zkService;
	
	@Test
	public void create() {
		zkService.create("/myzk/service/address", true);
	}
	
	@Test
	public void setData() {
		zkService.setData("/myzk/service/address", "AAA");
	}
}
