package com.madao.activemq.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.xml.soap.Text;

public class MyConsumer01 {

    private static final String BROKE_URL = "tcp://127.0.0.1:61616";
    private static final String QUEUE_NAME = "queue_01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKE_URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        while(true){
            //receive方法有两个重载方法，一个无参，一个需要传入一个long型时间。后者如果在指定时间内没有消费到消息，会断开消费连接，
            //而前者在未消费到消息前一直处于阻塞状态
            TextMessage message = (TextMessage) consumer.receive(5000);
            if(null != message){
                System.out.println(message.getText());
            }else{
                break;
            }
        }
        consumer.close();
        session.close();
        connection.close();

    }
}
