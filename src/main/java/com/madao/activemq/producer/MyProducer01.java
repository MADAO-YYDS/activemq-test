package com.madao.activemq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class MyProducer01 {

    private static final String BROKE_URL = "tcp://127.0.0.1:61616";
    private static final String QUEUE_NAME = "queue_01";


    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKE_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);
        for(int i=0;i<3;i++){
            TextMessage message = session.createTextMessage("hello world" + "---" + i);
            producer.send(message);
        }
        producer.close();
        session.close();
        connection.close();
    }
}
