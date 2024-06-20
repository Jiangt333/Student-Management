package com.example.authservice.service;

import org.springframework.mail.MailSender;

import java.util.Date;

public interface MailService {
    int verifyCode(String toEmail);
    String getCode();
    int sendMessageToMail(String toEmail);
}
