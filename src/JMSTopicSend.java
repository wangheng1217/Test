import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;

public class JMSTopicSend {
    TopicConnection connection;
    TopicSession subSession;
    Topic eWfmTopic;

    /* Constructor. Establish JMS publisher and subscriber */
    public JMSTopicSend(String topicName) throws Exception {
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
        
        eWfmTopic = (Topic) jndi.lookup(topicName);

        // Create a JMS connection
        connection = conFactory.createTopicConnection();

        // Create JMS session objects
        subSession = connection.createTopicSession(false,
                Session.AUTO_ACKNOWLEDGE);

        // Start the JMS connection; allows messages to be delivered
        connection.start();

    }

    public void sendMessage() throws java.io.IOException {
        String fileName = "C:\\Desktop\\PDC2.txt";
        
        StringBuilder msgText = new StringBuilder();
        TopicPublisher publisher = null;
        TextMessage textMsg = null;

        /* Get file contents of message file we are going to send. */

        BufferedReader msgFileRdr = new BufferedReader(new FileReader(fileName));
        String line = null;
        while ((line = msgFileRdr.readLine()) != null) {
            msgText.append(line + "\n"); 
        }

        /* Send JMS text message. */

        try {
            System.out.println(msgText.toString());
            
            publisher = subSession.createPublisher(eWfmTopic);
            textMsg = subSession.createTextMessage(msgText.toString());

            publisher.publish(textMsg);
        }
        catch (Exception e) {
            System.exit(3);
        }

        try {
            subSession.close();
            connection.close();
        }
        catch (JMSException e) {
            System.exit(4);
        }
    }

    public static void main(String[] args) throws java.io.IOException {
        JMSTopicSend producer = null;

        try {
            producer = new JMSTopicSend("com.twtc.cdi.ewfm.request.in");
        }
        catch (Exception e) {
            System.exit(1);
        }

        producer.sendMessage();
    } // main()
}