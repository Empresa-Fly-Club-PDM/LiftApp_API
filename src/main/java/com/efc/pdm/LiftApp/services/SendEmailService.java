package com.efc.pdm.LiftApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {
    @Autowired private JavaMailSender javaMailSender;
    public void sendEmail(String to, String body, String topic) {
        System.out.println("Sending email");
        SimpleMailMessage simpleMailmessage = new SimpleMailMessage();
        simpleMailmessage.setFrom("expensetrackeraca@gmail.com");
        simpleMailmessage.setTo(to);
        simpleMailmessage.setSubject(topic);
        simpleMailmessage.setText(body);
        javaMailSender.send(simpleMailmessage);
        System.out.println("Correo Enviado...");
    }
}