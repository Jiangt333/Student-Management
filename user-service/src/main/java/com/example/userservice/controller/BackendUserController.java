package com.example.userservice.controller;

import com.example.userservice.model.BackendUser;
import com.example.userservice.model.User;
import com.example.userservice.service.BackendUserService;
import com.example.userservice.service.UserService;
import com.example.userservice.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Api(tags = {"后台用户接口文档"})
@RestController
@RequestMapping("/backend_user")
@EnableSwagger2
public class BackendUserController {
    @Autowired
    private BackendUserService backendUserService;
    @Autowired
    private Response response;

    @ApiOperation(value = "获取全部后台用户的全部信息")
    @GetMapping("/all-users")
    @ResponseBody
    public Response<List<BackendUser>> getAllUsers() {
        List<BackendUser> backendUserList = backendUserService.getAllBackendUsers();
        response.data = backendUserList;
        response.code = 200;
        return response;
    }

}
