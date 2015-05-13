import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JMSTopicTest implements javax.jms.MessageListener {

    /* Constructor. Establish JMS publisher and subscriber */
    public JMSTopicTest(String topicName) throws Exception {
        // Obtain a JNDI connection
        Properties env = new Properties();
        // ... specify the JNDI properties specific to the vendor
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://cdiprd03.twtelecom.com:6001");

        InitialContext jndi = new InitialContext(env);

        // Look up a JMS connection factory
        TopicConnectionFactory conFactory = (TopicConnectionFactory) jndi
                .lookup("weblogic.jms.ConnectionFactory");

        // Create a JMS connection
        TopicConnection connection = conFactory.createTopicConnection();

        // Create JMS session objects
        TopicSession subSession = connection.createTopicSession(false,
                Session.AUTO_ACKNOWLEDGE);

        // Look up a JMS topic
        Topic testTopic = (Topic) jndi.lookup(topicName);

        // Create a JMS subscriber
        TopicSubscriber subscriber = subSession.createSubscriber(testTopic);

        // Set a JMS message listener
        subscriber.setMessageListener(this);

        // Start the JMS connection; allows messages to be delivered
        connection.start();

    }

    /* Receive message from topic subscriber */
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            System.out.println(text);
        } catch (Exception jmse) {
            System.out.println(jmse);
        }
    }

    /* Run the test client */
    public static void main(String[] args) throws Exception {
        JMSTopicTest test = new JMSTopicTest("com.twtc.cdi.ewfm.request.in");
        
        while(true);
    }
}