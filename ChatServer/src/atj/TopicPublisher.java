package atj;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Topic;

public class TopicPublisher {

	public void publishTopicMessages(String topicName, String msg) {
		ConnectionFactory connectionFactory = new com.sun.messaging.ConnectionFactory();
		JMSContext jmsContext = connectionFactory.createContext();
		JMSProducer jmsProducer = jmsContext.createProducer();
		try {
			Topic topic = new com.sun.messaging.Topic(topicName);
			jmsProducer.send(topic, msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		jmsContext.close();
	}
}
