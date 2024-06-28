package com.example.authservice.controller;

import com.example.authservice.utils.JWTUtils;
import com.example.authservice.service.AuthService;
import com.example.authservice.utils.Response;
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
    @Autowired
    private Response response;

    @GetMapping("/login/user")
    @ResponseBody
    public Response<String> userLogin(String SID, String password) {
        if(authService.userLogin(SID, password)){
            Map<String, String> payLoad = new HashMap<>();
            payLoad.put("id", SID);
            String token = JWTUtils.getToken(payLoad);
            response.data = token;
            response.code = 200;
        }
         else {
            response.code = 4004;
            response.data = "password or id wrong!";
        }
        return response;
    }

    @GetMapping("/login/backend_user")
    @ResponseBody
    public Response<String> backendUserLogin(String SID, String password) {
        if(authService.backendUserLogin(SID, password)){
            Map<String, String> payLoad = new HashMap<>();
            payLoad.put("id", SID);
            String token = JWTUtils.getToken(payLoad);
            response.data = token;
            response.code = 200;
        }
        else {
            response.code = 4004;
            response.data = "password or id wrong!";
        }
        return response;
    }

    @PutMapping("/password")
    @ResponseBody
    public Response<String> updateUserPassword(String SID, String password) {
        if(authService.updateUserPassword(SID, password)){
            response.data = "修改成功！";
            response.code = 200;
        }
        else {
            response.code = 4004;
            response.data = "修改失败，请检查SID是否正确！";
        }
        return response;
    }

    @PutMapping("/backend_password")
    @ResponseBody
    public Response<String> updateBackendUserPassword(String SID, String password) {
        if(authService.updateBackendUserPassword(SID, password)){
            response.data = "修改成功！";
            response.code = 200;
        }
        else {
            response.code = 4004;
            response.data = "修改失败，请检查SID是否正确！";
        }
        return response;
    }
}
