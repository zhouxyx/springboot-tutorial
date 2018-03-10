package com.xxx.springboot.activemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xxx.springboot.rabbitmq.Sender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MQApplicationTest {

	@Autowired
	private ActiveMQProvider activeMQProvider;

	@Test
	public void send() {
		/*long s = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			activeMQProvider.send("ActiveMQ");
		}
		long e = System.currentTimeMillis();
		System.out.println(e - s);*/
		activeMQProvider.send("ActiveMQ");
		activeMQProvider.sendTopic("ActiveMQ Topic");
	}

	@Autowired
	private Sender sender;

	@Test
	public void hello() throws Exception {
		long s = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			sender.send();
		}
		long e = System.currentTimeMillis();
		System.out.println(e - s);
	}

}
