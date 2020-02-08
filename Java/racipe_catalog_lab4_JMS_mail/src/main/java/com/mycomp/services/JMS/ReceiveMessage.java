package com.mycomp.services.JMS;

import com.mycomp.config.JMSConfig;
import com.mycomp.model.dao.DoctorDAO;
import com.mycomp.model.entity.History;
import com.mycomp.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.jms.*;


@Component
public class ReceiveMessage {

    private HistoryService historyService;

    @Autowired
    JmsTemplate jmsTemplate;


        @Autowired
    public void setHistoryService(HistoryService historyService) {

        this.historyService = historyService;
    }

    @JmsListener(destination = "my_topic")
    public void receiveMessage(Message message) throws JMSException {
        //System.out.println(message);
        ObjectMessage objectMessage = (ObjectMessage) message;
        System.out.println(objectMessage);
        History h1 =  (History) jmsTemplate.receive("my_topic");
        System.out.println(h1);
        if(objectMessage.getObject() instanceof History) {
            History history = (History) objectMessage.getObject();
            try {
                historyService.add(history);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

    }
}