package com.mycomp.services.JMS;

import com.mycomp.model.entity.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component("objectMessageProducer")
public class ObjectMessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public ObjectMessageProducer(){

    }
    public void sendMessage(History history) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage();
                objectMessage.setObject(history);
                return objectMessage;
            }
        });
    }

}