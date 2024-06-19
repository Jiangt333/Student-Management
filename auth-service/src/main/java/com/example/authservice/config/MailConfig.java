package com.example.authservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Random;

@Configuration
public class MailConfig {
    @Value("${spring.mail.username}")
    private static String USERNAME;
    @Value("${spring.mail.password}")
    private static String PASSWORD;

    /**
     * 配置 MailSender
     * MailSender是一个接口，其一个实现类为JavaMailSenderImpl，java自带的
     * @return
     */
    @Bean
    public MailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");  //指定用来发送邮件服务器的主机名
        mailSender.setPort(587);    // 默认端口，标准的SMTP端口
        mailSender.setUsername(USERNAME);  //配置自己的qq邮箱
        mailSender.setPassword(PASSWORD);   //配置自己的qq邮箱发送授权码
        return mailSender;
    }

    /**
     * 生成六位随机数
     */
    @Bean
    public String getCode(){
        Random random = new Random();
        String result = "";
        for(int i = 0; i < 6; i++){
            result += random.nextInt(10);
        }
        return result;
    }
}
