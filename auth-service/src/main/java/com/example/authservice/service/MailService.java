package com.example.authservice.service;

import org.springframework.mail.MailSender;

import java.util.Date;

public interface MailService {
    int verifyCode(String toEmail);
    int verifyCode(String SID, String toEmail, String userCode);
    String getCode();
    int sendMessageToMail(String toEmail);
}
