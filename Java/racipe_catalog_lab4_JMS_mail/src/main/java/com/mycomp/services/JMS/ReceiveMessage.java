package com.mycomp.services.JMS;

import com.google.gson.Gson;
import com.mycomp.model.entity.History;
import com.mycomp.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


import javax.jms.*;


@Component
public class ReceiveMessage {

    @Autowired
    private HistoryService historyService;


 @JmsListener(destination = "history_message")
 public void receive(TextMessage textMessage) throws JMSException {
    try {
        History history = (History) new Gson().fromJson(textMessage.getText(), History.class);
        historyService.add(history);
        System.out.println("Receive history: "+history);
    }
    catch (Exception e) {
        e.printStackTrace();
    }

 }
}
