package com.example.authservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
//    @Value("${spring.mail.username}")
//    private static String USERNAME;
//    @Value("${spring.mail.password}")
//    private static String PASSWORD;

    //    @Value("${mail.username}")
    private String USERNAME = "";
    //    @Value("${mail.password}")
    private String PASSWORD = "";

    /**
     * 配置 MailSender
     * MailSender是一个接口，其一个实现类为JavaMailSenderImpl，java自带的
     * @return
     */
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.qq.com");
        mailSender.setPort(587);
        mailSender.setUsername(USERNAME);
        mailSender.setPassword(PASSWORD);

//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.ehlo", "true");
//        props.put("mail.debug", "true");

        return mailSender;
    }
}
