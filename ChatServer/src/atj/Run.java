package atj;

import javax.jms.JMSException;

public class Run {

	public static void main(String[] args) {
		QueueConsumer queueConsumer = new QueueConsumer("localhost:7676/jms", "ATJQueue");
		TopicPublisher topicPublisher = new TopicPublisher();
		for (int i = 0; i < 3600; i++) {
			String msg = null;
			System.out.println("Czekam");
			try {
				if ((msg = queueConsumer.receiveQueueMessage()) != null) {
					topicPublisher.publishTopicMessages("ATJTopic", msg);
				}
				Thread.sleep(1000);
			} catch (JMSException | InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
