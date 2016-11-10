package com.jamp.io.jms;

import com.jamp.io.enums.JmsEventType;
import com.jamp.io.utils.servicebeans.UserLoginEvent;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * JMS message consumer as separate java application
 * Has separate session for logging activity messages that require acknowledgement
 * and session for simple page activities that don't need additional acknowledgement
 */
public class JmsMessageConsumer {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://localhost:61616");
		Destination destination = new ActiveMQQueue("someQueue");
		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		Session session2 = connection.createSession(false,
				Session.CLIENT_ACKNOWLEDGE);
		try {
			MessageConsumer consumerLogin = session2.createConsumer(destination, "MessageType='" + JmsEventType.USER_LOGIN_EVENT + "'");
			MessageConsumer pageEvent = session.createConsumer(destination, "MessageType='" + JmsEventType.PAGE_EVENT + "'");
			connection.start();
			TextMessage msg;
			ObjectMessage logEvent;
			while (true) {
				try {
					logEvent = (ObjectMessage) consumerLogin.receive(100);
					if (logEvent != null) {
						System.out.println("Received: " + ((UserLoginEvent) logEvent.getObject()).toString());
						logEvent.acknowledge();
					}
					msg = (TextMessage) pageEvent.receive(100);
					if (msg != null)
						System.out.println("Received: " + msg.getText());

				} finally {
				}
			}
		} finally {
			session.close();
			session2.close();
			connection.close();
		}

	}

}