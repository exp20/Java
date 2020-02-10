package com.mycomp.services.JMS;

import com.mycomp.model.entity.EmailHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(EmailHistory emailHistory){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("Me");
        mailMessage.setSubject("Some change");
        mailMessage.setText(emailHistory.getCondition());
        mailMessage.setTo(emailHistory.getEmail());
        javaMailSender.send(mailMessage);
    }
}
