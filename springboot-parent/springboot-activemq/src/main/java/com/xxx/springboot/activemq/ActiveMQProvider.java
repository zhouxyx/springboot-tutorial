package com.xxx.springboot.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQProvider {

    @Autowired
    private JmsTemplate queueJmsTemplate;
    
    @Autowired
    private JmsTemplate topicJmsTemplate;

    public void send(String message) {
    	queueJmsTemplate.convertAndSend("message", message);
    }
    
    public void sendTopic(String message) {
    	topicJmsTemplate.convertAndSend("sample.topic", message);
    }
    
}