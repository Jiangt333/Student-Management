package com.example.userservice.controller;

import com.example.userservice.utils.Response;
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
    public Response<List<User>> getAllUsers() {
        List<User> UserList = userService.getAllUsers();
        response.data = UserList;
        response.code = 200;
        return response;
    }

    @ApiOperation(value = "根据学生学号SID获取某个用户全部信息")
    @GetMapping("/one-user")
    @ResponseBody
    public Response<User> getUserById(@ApiParam(value = "学号") @RequestParam(value = "SID") String SID) {
        User user = userService.getUserById(SID);
        response.data = user;
        response.code = 200;
        return response;
    }

    @ApiOperation(value = "根据学生学号SID在学生端更新个人信息")
    @PutMapping("/stu_userinfo")
    @ResponseBody
    public Response<String> updateUserById(@RequestBody User user) {
        if(userService.updateUserById(user) == 1){
            response.data = "更新成功";
            response.code = 200;
        }
        else if(userService.updateUserById(user) == -1){
            response.data = "没有匹配的条目，请检查SID是否正确";
            response.code = 4006;
        }
        else{
            response.data = "更新失败";
            response.code = 4006;
        }
        return response;
    }
}
