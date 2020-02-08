package com.mycomp.services.JMS;

import com.mycomp.model.entity.History;
import com.mycomp.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
/*
@Service
public class Recive3 implements MessageListener {

    @Autowired
    private HistoryService historyService;

    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            if (objectMessage.getObject() instanceof History) {
                History history = (History) objectMessage;
                try {
                    historyService.add(history);
                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}*/