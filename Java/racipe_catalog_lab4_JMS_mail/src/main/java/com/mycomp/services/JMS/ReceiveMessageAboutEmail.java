package com.mycomp.services.JMS;

import com.google.gson.Gson;
import com.mycomp.model.dao.EmailHistoryDAO;
import com.mycomp.model.entity.EmailHistory;
import com.mycomp.services.EmailHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class ReceiveMessageAboutEmail {

    @Autowired
    EmailHistoryService emailHistoryService;

    @Autowired
    EmailSender emailSender;

    @JmsListener(destination = "email_history_message")
    public void receive(TextMessage textMessage) throws JMSException {
        EmailHistory emailHistory = new Gson().fromJson(textMessage.getText(),EmailHistory.class);
        emailSender.sendEmail(emailHistory);
        System.out.println("Receive email history"+ emailHistory);
        try {
            emailHistoryService.add(emailHistory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
