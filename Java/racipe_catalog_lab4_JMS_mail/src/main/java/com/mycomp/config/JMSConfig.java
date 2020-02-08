package com.mycomp.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.messaging.handler.annotation.DestinationVariable;

import javax.jms.Destination;

@Configuration
@EnableJms
@ComponentScan(basePackages = "com.mycomp")
public class JMSConfig {

    String BROKER_URL = "tcp://localhost:61616";
    String BROKER_USERNAME = "admin";
    String BROKER_PASSWORD = "admin";

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setPassword(BROKER_USERNAME);
        connectionFactory.setUserName(BROKER_PASSWORD);
        return connectionFactory;
    }


    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setPubSubDomain(true); // для работы с topic
        //утсановка места назначения для отправки и приема по умалчанию
       Destination defaultDestination = new ActiveMQTopic("my_topic");
       template.setDefaultDestination(defaultDestination);

        return template;
    }



    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");

        factory.setPubSubDomain(true); // для работы с topic
        return factory;
    }

}