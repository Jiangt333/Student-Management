package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"用户接口文档"})
@RestController
@RequestMapping("/user")
@EnableSwagger2
public class userController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @ResponseBody
    public User login(String SID, String SPassword) {
        User userDB = userService.login(SID, SPassword);
        return userDB;
    }

    @ApiOperation(value = "获取全部用户的全部信息", tags = {"用户信息的接口文档"})
    @GetMapping("/all-users")
    @ResponseBody
    public List<User> hello() {
        List<User> UserList = userService.getAllUsers();
        return UserList;
    }

    @ApiOperation(value = "获取某个用户全部信息", tags = {"用户信息的接口文档"})
    @GetMapping("/one-user")
    @ResponseBody
    public User hello(@ApiParam(value = "学号") @RequestParam(value = "SID") int SID) {
        User User = userService.getUserById(SID);
        return User;
    }



}
