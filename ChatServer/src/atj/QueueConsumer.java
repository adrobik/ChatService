package atj;

import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;

public class QueueConsumer {
	private JMSContext jmsContext;
	private JMSConsumer jmsConsumer;
	private Queue queue;

	public QueueConsumer(String url, String queueName) {
		ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();
		jmsContext = connectionFactory.createContext();
		try {
			((com.sun.messaging.ConnectionFactory) connectionFactory)
					.setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList, url);
			queue = new com.sun.messaging.Queue(queueName); // "ATJQueue"
			jmsConsumer = jmsContext.createConsumer(queue);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public String receiveQueueMessage() throws JMSException {
		Message msg = jmsConsumer.receive(10); // 10 ms
		if (msg instanceof TextMessage)
			return ((TextMessage) msg).getText();
		return null;
	}

	protected void finalize() {
		if (jmsConsumer != null)
			jmsConsumer.close();
		if (jmsContext != null)
			jmsContext.close();
	}
}
