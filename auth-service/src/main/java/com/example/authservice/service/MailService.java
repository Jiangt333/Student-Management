package com.example.authservice.service;

import org.springframework.mail.MailSender;

import java.util.Date;

public interface MailService {
    void verifyCode(String toEmail, String code);
    void sendMessageToMail(MailSender mailSender, String toEmail, String code);
}
