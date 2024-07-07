package com.example.userservice.controller;

import com.example.userservice.model.BackendUser;
import com.example.userservice.service.BackendUserService;
import com.example.userservice.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @ApiOperation(value = "根据筛选项获取后台用户的信息")
    @GetMapping("/all-users")
    @ResponseBody
    public Response<List<BackendUser>> getAllBackendUsers(String SID, String name, Integer role, String grade, String user_class) {
        List<BackendUser> backendUserList = backendUserService.getAllBackendUsers(SID, name, role, grade, user_class);
        response.data = backendUserList;
        response.code = 200;
        return response;
    }

    @ApiOperation(value = "获取后台用户表的人数")
    @GetMapping("/user-num")
    @ResponseBody
    public Response<Integer> getBackendUserNum() {
        response.data = backendUserService.getBackendUserNum();
        response.code = 200;
        return response;
    }



    @ApiOperation(value = "根据SID获取后台用户信息")
    @GetMapping("/user")
    @ResponseBody
    public Response<BackendUser> getBackendUser(String SID) {
        BackendUser re = backendUserService.findBackendUser(SID);
        if(re != null){
            response.data = re;
            response.code = 200;
        }else{
            response.data = "不存在该后台用户！";
            response.code = 4004;
        }
        return response;
    }

    @ApiOperation(value = "根据SIDList删除后台用户信息")
    @DeleteMapping("/user")
    @ResponseBody
    public Response<String> deleteBackendUser(@RequestParam List<String> SIDList) {
        if(backendUserService.deleteBackendUser(SIDList) == 1){
            response.data = "删除成功！";
            response.code = 200;
        }else{
            response.data = "不存在该后台用户！";
            response.code = 4004;
        }
        return response;
    }

    @ApiOperation(value = "添加后台用户信息")
    @PostMapping("/user")
    @ResponseBody
    public Response<String> addBackendUser(@RequestBody BackendUser backendUser) {
        int re = backendUserService.addBackendUser(backendUser);
        if(re == 1){
            response.data = "添加成功！";
            response.code = 200;
        } else if(re == -1){
            response.data = "已经存在该SID的后台用户！";
            response.code = 4005;
        } else{
            response.data = "添加失败！";
            response.code = 4004;
        }
        return response;
    }

    @ApiOperation(value = "根据SID修改后台用户信息")
    @PutMapping("/user")
    @ResponseBody
    public Response<String> updateBackendUser(BackendUser backendUser) {
        int re = backendUserService.updateBackendUser(backendUser);
        if(re == 1) {
            response.data = "修改成功！";
            response.code = 200;
        } else{
            response.data = "修改失败，不存在该后台用户或其他异常！";
            response.code = 4004;
        }
        return response;
    }

}
