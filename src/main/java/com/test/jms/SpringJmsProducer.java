package com.test.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SpringJmsProducer {
	
	private JmsTemplate jmsTemplate;
    private Destination destination; // default destination -> messageQueue1
    
    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
    
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    
    public Destination getDestination() {
        return this.destination;
    }
    
    public void setDestination(Destination destination) {
        this.destination = destination;
    }
    
    public void sendMessage(final String msg) {
        System.out.println("Producer sends " + msg);
        this.jmsTemplate.send(this.destination, new MessageCreator() {
        	@Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
    
    public void sendRealMessage(final String msg, final int companyIdx) {
        System.out.println("Producer sends " + msg);
        
        // company + "" // send a message to specified destination
        this.jmsTemplate.send(companyIdx + "", new MessageCreator() {
        	@Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg); // 그대로 실행!
            }
        });
    }
    
}
