package com.madao.activemq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MyProducer02 {

    private static final String BROKE_URL = "tcp://127.0.0.1:61616";
    private static final String TOPIC_NAME = "topic-01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKE_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);
        for(int i=0;i<3;i++){
            TextMessage textMessage = session.createTextMessage("message_" + i);
            producer.send(textMessage);
        }
        producer.close();
        session.close();
        connection.close();
    }
}
