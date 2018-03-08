package com.xxx.springboot.activemq;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQProvider {

    @Autowired
    private JmsTemplate jmsTemplate;
    
    

    public void send(String message) {
        jmsTemplate.convertAndSend("message", message);
    }
    
    
    /*public void sendTopic(String message) {
    	jmsTemplate.convertAndSend(new ActiveMQTopic("sample.topic"), message);
    }*/
    
}