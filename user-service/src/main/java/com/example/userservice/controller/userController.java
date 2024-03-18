package com.example.userservice.controller;

import com.example.userservice.model.user;
import com.example.userservice.service.userService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.List;

@Api(tags = {"用户信息的接口文档"})
@RestController
@RequestMapping("/userinfo")
@EnableSwagger2
public class userController {

    @Autowired
    private userService UserService;

    @ApiOperation(value = "获取全部用户的全部信息", tags = {"用户信息的接口文档"})
    @GetMapping("/all-users")
    @ResponseBody
    public List<user> hello() {
        List<user> UserList = UserService.getAllUsers();
        return UserList;
    }

    @ApiOperation(value = "获取某个用户全部信息", tags = {"用户信息的接口文档"})
    @GetMapping("/one-user")
    @ResponseBody
    public user hello(@ApiParam(value = "学号") @RequestParam(value = "id", defaultValue = "0") int id) {
        user User = UserService.getUserById(id);
        return User;
    }

}
