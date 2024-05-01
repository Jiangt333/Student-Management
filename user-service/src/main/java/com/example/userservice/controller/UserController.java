package com.example.userservice.controller;

import com.example.authservice.utils.Response;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.List;

@Api(tags = {"用户接口文档"})
@RestController
@RequestMapping("/user")
@EnableSwagger2
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private Response response;

    @ApiOperation(value = "获取全部用户的全部信息")
    @GetMapping("/all-users")
    @ResponseBody
    public Response<List<User>> hello() {
        List<User> UserList = userService.getAllUsers();
        response.data = UserList;
        return response;
    }

    @ApiOperation(value = "获取某个用户全部信息")
    @GetMapping("/one-user")
    @ResponseBody
    public Response<User> hello(@ApiParam(value = "学号") @RequestParam(value = "SID") String SID) {
        User user = userService.getUserById(SID);
        response.data = user;
        return response;
    }



}
