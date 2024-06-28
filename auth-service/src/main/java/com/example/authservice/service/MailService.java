package com.example.authservice.service;

import org.springframework.mail.MailSender;

import java.util.Date;

public interface MailService {
    int verifyCodeFirst(String SID, String toEmail);
    int verifyCode(String toEmail, String userCode);
    String getCode();
    int sendMessageToMail(String SID, String toEmail);
}
