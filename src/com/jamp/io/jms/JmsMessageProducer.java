package com.jamp.io.jms;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
 
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import com.jamp.io.enums.JmsEventType;
import com.jamp.io.enums.LoginEventType;
import com.jamp.io.utils.servicebeans.UserLoginEvent;

/**
 * JMS message producer that is used by spring application
 * Uses two separate messages for sending messages with auto-acknowledgement 
 * and with client-acknowledgement
 */
@Service
public class JmsMessageProducer {
	Session session = null;
	Session session2 = null;
    Destination destination = new ActiveMQQueue("someQueue");

    /**
     * Sends events related to user login activity
     * Acknowledgement must be made by consumer
     */
	public void sendUserLogin(UserLoginEvent message) {
		if(session == null) {
			initializeSession();
		}
        Message msg;
		try {
			msg = session2.createObjectMessage(message);
			msg.setStringProperty("MessageType", JmsEventType.USER_LOGIN_EVENT.toString());
			msg.setJMSPriority(message.eventType == LoginEventType.LOGIN_FAILED ? 9 : 0);
	        MessageProducer producer = session2.createProducer(destination);
	        producer.send(msg);
	        System.out.println("Send text '" + message + "'");
		} catch (JMSException e) {
			e.printStackTrace(); // Sorry for this 
		}
	}
	
	/**
	 *  Sends events related to user activity on page
	 *  Acknowledgement is not necessary
	 */
	public void sendPageActivity(String message) {
		if(session == null) {
			initializeSession();
		}
        String payload = message;
        Message msg;
		try {
			msg = session.createTextMessage(payload);
			msg.setStringProperty("MessageType", JmsEventType.PAGE_EVENT.toString());
	        MessageProducer producer = session.createProducer(destination);
	        producer.send(msg);
	        System.out.println("Send text '" + payload + "'");
		} catch (JMSException e) {
			e.printStackTrace(); // Double sorry
		}
	}
	
	private void initializeSession() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        destination = new ActiveMQQueue("someQueue");
        Connection connection = null;
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			session2 = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}