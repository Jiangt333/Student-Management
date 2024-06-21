package com.example.authservice.controller;

import com.example.authservice.service.AuthService;
import com.example.authservice.service.MailService;
import com.example.authservice.utils.Response;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Api(tags = {"邮箱验证文档"})
@RestController
@RequestMapping("/mail")
@EnableSwagger2
public class MailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private Response response;

    @GetMapping("/send")
    @ResponseBody
    public Response<String> sendMessageToMail(String toEmail){
//        int re = mailService.sendMessageToMail(toEmail);
        if(mailService.sendMessageToMail(toEmail) == 1){
            response.code = 200;
            response.data = "发送成功！";
        }
        else{
//            long timeDifferenceInMinutes = (300 - re) / 60;
//            long remainingSeconds = (300 - re) % 60;
//            response.code = 200;
//            response.data = "上一个验证码尚未过期，请不要频繁请求！请在" + timeDifferenceInMinutes + "分" + remainingSeconds + "秒后重试";
            response.code = 200;
            response.data = "上一个验证码尚未过期，请不要频繁请求！";
        }
        return response;
    }

    @GetMapping("/valid")
    @ResponseBody
    public Response<String> validCode(String toEmail, String userCode){
        int re = mailService.verifyCode(toEmail, userCode);
        if(re == 1){
            response.code = 200;
            response.data = "验证成功！";
        }
        else if(re == -1){
            response.code = 4004;
            response.data = "验证码已过期！";
        }
        else if(re == 0){
            response.code = 4004;
            response.data = "验证失败！";
        }
        return response;
    }
}
