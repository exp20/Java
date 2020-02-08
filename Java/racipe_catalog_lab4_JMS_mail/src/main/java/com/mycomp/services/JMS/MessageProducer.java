package com.mycomp.services.JMS;


import com.mycomp.model.entity.History;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;


@Component
public class MessageProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(History history) {
        jmsTemplate.convertAndSend("my_topic",history);
       /* jmsTemplate.send("my_topic", new MessageCreator() {
                    public Message createMessage(Session session) throws JMSException {
                        ObjectMessage objectMessage = session.createObjectMessage(history);
                        return objectMessage;
                    }
                }
        );*/
    }
}