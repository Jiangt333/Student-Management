package com.example.authservice.service.impl;

import com.example.authservice.config.MailConfig;
import com.example.authservice.dao.MailDao;
import com.example.authservice.model.MailCode;
import com.example.authservice.service.MailService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class MailServiceImpl implements MailService {

    private String USERNAME = "scecs4sysusse@163.com";

    @Autowired
    MailDao mailDao;
    @Autowired
    MailConfig mailConfig;

    private long timeDifferenceInSeconds;

    @Override
    public int verifyCodeFirst(String SID, String toEmail){
        if(mailDao.validIdentity(SID, toEmail) == null){
            return 2;
        }
        List<MailCode> mailCodeList = mailDao.selectCode(toEmail);
        if(mailCodeList.size() == 1){
            MailCode mailCode = mailCodeList.get(0);
            // 验证验证码是否过期
            ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("GMT+8"));
            ZonedDateTime startTime = mailCode.getStart_time().toInstant().atZone(ZoneId.of("GMT+8"));

            // 计算时间差（以秒为单位）
            Duration timeDifference = Duration.between(startTime, currentTime);
            timeDifferenceInSeconds = timeDifference.getSeconds();

            // 将时间差转换为分钟和秒
//            long timeDifferenceInMinutes = timeDifferenceInSeconds / 60;
//            long remainingSeconds = timeDifferenceInSeconds % 60;

            // 打印时间差和时间戳以进行调试
//            System.err.println("timeDifferenceInMinutes: " + timeDifferenceInMinutes + " timeDifferenceInSeconds: " + timeDifferenceInSeconds + " remainingSeconds: " + remainingSeconds + " currentTime: " + currentTime + " startTime: " + startTime);
            System.err.println(" timeDifferenceInSeconds: " + timeDifferenceInSeconds + ", currentTime: " + currentTime + ", startTime: " + startTime);

            if (timeDifferenceInSeconds > 180) {
                // 删除过期的验证码记录
                System.err.println("timeDifferenceInSeconds > 3分钟，验证码已过期！");
                mailDao.deleteCode(toEmail);
                return 1;
            }
            return 0;
        }
//        else if(mailCodeList.size() > 1){
//            // 正常不应该出现这种情况
//        }
        return 1;
    }

    @Override
    public int verifyCode(String toEmail, String userCode){
        List<MailCode> mailCodeList = mailDao.selectCode(toEmail);
        if(mailCodeList.size() == 1){
            // 对应邮箱的验证存在
            MailCode mailCode = mailCodeList.get(0);

            // 验证验证码是否过期
            ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("GMT+8"));
            ZonedDateTime startTime = mailCode.getStart_time().toInstant().atZone(ZoneId.of("GMT+8"));

            // 计算时间差（以秒为单位）
            Duration timeDifference = Duration.between(startTime, currentTime);
            timeDifferenceInSeconds = timeDifference.getSeconds();

            System.err.println(" timeDifferenceInSeconds: " + timeDifferenceInSeconds + ", currentTime: " + currentTime + ", startTime: " + startTime);

            if (timeDifferenceInSeconds > 180) {
                // 删除过期的验证码记录
                System.err.println("timeDifferenceInSeconds > 3分钟，验证码已过期！");
                mailDao.deleteCode(toEmail);
                return -1;
            }
            else {
                // 验证码存在且没过期，则验证是否相等
                return (Objects.equals(userCode, mailCode.getCode())) ? 1 : 0;
            }
        }
        // 对应邮箱的验证不存在
        return 0;
    }

    /**
     * 生成六位随机数
     */
    @Override
    public String getCode(){
        Random random = new Random();
        String result = "";
        for(int i = 0; i < 6; i++){
            result += random.nextInt(10);
        }
        return result;
    }

    @Override
    public int sendMessageToMail(String SID, String toEmail){

        System.err.println("您本次的发送对象是：" + toEmail);
        int re = verifyCodeFirst(SID, toEmail);
        if(re == 0){
            return 0;
        }
        else if(re == 2){
            return 2;
        }

        // 获得配置好的配置 MailSender
        JavaMailSender mailSender = mailConfig.getJavaMailSender();
        // 或直接：
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.qq.com");
//        mailSender.setPort(587);
//        mailSender.setUsername(USERNAME);
//        mailSender.setPassword(PASSWORD);

        // 获取验证码
        String code = getCode();
        // 发送邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(USERNAME);
        message.setTo(toEmail);
        message.setSubject("【中山大学软件工程学院学生管理平台】用户找回密码");
        message.setText("您本次的验证码是：" + code + "，有效期3分钟。请妥善保管，切勿泄露");
        mailSender.send(message);

        // 保存到数据库
        mailDao.saveCode(toEmail, code, Date.from(Instant.now()));

        System.out.println("您本次的验证码是：" + code);
        return 1;
    }



}
