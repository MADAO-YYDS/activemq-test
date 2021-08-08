package com.madao.activemq.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MyConsumer04 {

    private static final String BROKE_URL = "tcp://127.0.0.1:61616";
    private static final String QUEUE_NAME = "queue_01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKE_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        Message message = consumer.receive();
        while(null != message){
            TextMessage textMessage = (TextMessage) message;
            System.out.println(textMessage.getText());
            message = consumer.receive();
        }
        consumer.close();
        session.close();
        connection.close();

    }
}
