package com.example.authservice.controller;

import com.example.authservice.utils.JWTUtils;
import com.example.authservice.service.AuthService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.Map;

@Api(tags = {"登录鉴权接口文档"})
@RestController
@RequestMapping("/auth")
@EnableSwagger2
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String SID, String SPassword) {
        Map<String, Object> result = new HashMap<>();
        if(authService.login(SID, SPassword)){
            Map<String, String> payLoad = new HashMap<>();
            payLoad.put("id", SID);
            String token = JWTUtils.getToken(payLoad);
            result.put("token", token);
            result.put("state", true);
            result.put("msg", "登录成功！");
        }
         else {
            result.put("state","false");
            result.put("msg", "登录失败！");
        }
        return result;
    }
}
