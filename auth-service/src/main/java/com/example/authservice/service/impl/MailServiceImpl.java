package com.example.authservice.service.impl;

import com.example.authservice.dao.MailDao;
import com.example.authservice.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private static String username;

    @Autowired
    MailDao mailDao;

    @Override
    public void verifyCode(String toEmail, String code){

    }


    @Override
    public void sendMessageToMail(MailSender mailSender, String toEmail, String code){



        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(toEmail);
        message.setSubject("【中山大学软件工程学院学生管理平台】用户找回密码");
        message.setText("您本次的验证码是：" + code + "，有效期5分钟。请妥善保管，切勿泄露");
//        message.setText("您本次的验证码是：" + code);
        mailSender.send(message);

        // 保存到数据库
        mailDao.saveCode(toEmail, code, new Date());

        System.out.println("您本次的验证码是：" + code);
    }



}
