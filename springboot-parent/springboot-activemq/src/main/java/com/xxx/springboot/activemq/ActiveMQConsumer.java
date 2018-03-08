package com.xxx.springboot.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQConsumer {
	@JmsListener(destination = "message", containerFactory = "jmsListenerContainerQueue")
	public void receive(String message) {
		//System.out.println("收到的 message 是：" + message);
	}

	/*@JmsListener(destination = "sample.topic", containerFactory = "jmsListenerContainerTopic")
	public void receiveTopic(String message) {
		System.out.println("topic1：" + message);
	}*/

	/*@JmsListener(destination = "sample.topic", containerFactory = "jmsListenerContainerTopic")
	public void receiveTopic2(String message) {
		System.out.println("topic2：" + message);
	}*/

}