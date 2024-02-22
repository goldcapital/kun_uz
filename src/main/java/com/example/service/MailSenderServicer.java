package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServicer {
    @Autowired
    private JavaMailSender javaMailSender;
   /* @Value("${jumaniyazov.bobur@mail.ru}")
    private String fromAccount;*/
    /*public void sendEmail(String toAccount, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toAccount);
        msg.setFrom(fromAccount);
        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);*/
    }


