package com.mycomp.services.JMS;

import com.mycomp.config.JMSConfig;
import com.mycomp.model.dao.DoctorDAO;
import com.mycomp.model.entity.History;
import com.mycomp.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.jms.*;


@Component
public class ReceiveMessage { // implements SessionAwareMessageListener<ObjectMessage>

    private HistoryService historyService;

    @Autowired
    public void setHistoryService(HistoryService historyService) {

        this.historyService = historyService;
    }
    @JmsListener(destination = "my_topic")
    public void receiveMessage(final Message message) throws JMSException {
        System.out.println("ХУЕТА");
        ObjectMessage objectMessage = (ObjectMessage) message;

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