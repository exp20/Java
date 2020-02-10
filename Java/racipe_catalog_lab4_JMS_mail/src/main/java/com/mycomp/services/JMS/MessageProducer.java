package com.mycomp.services.JMS;


import com.google.gson.Gson;
import com.mycomp.model.entity.EmailHistory;
import com.mycomp.model.entity.History;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.lang.reflect.Type;
import java.util.Map;


@Component
public class MessageProducer {

    @Autowired
    JmsTemplate jmsTemplate;



/// Лучше не сериализовывать объект в сообщении тк как безопастность брокера и jms не дает десериализовать недоверенные пакеты
    public void sendMessage(History history) {
        String map = new Gson().toJson(history);
       jmsTemplate.send("history_message", new MessageCreator() {
           public TextMessage createMessage(Session session) throws JMSException {
               TextMessage textMessage = session.createTextMessage(map);
               return textMessage;
                    }
                }
        );

    }

    public void sendMessage(EmailHistory emailHistory) {
        String map = new Gson().toJson(emailHistory);
        jmsTemplate.send("email_history_message", new MessageCreator() {
                    public TextMessage createMessage(Session session) throws JMSException {
                        TextMessage textMessage = session.createTextMessage(map);
                        return textMessage;
                    }
                }
        );

    }
}