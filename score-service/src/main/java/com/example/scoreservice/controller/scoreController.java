package com.example.scoreservice.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Api(tags = {"成绩信息的接口文档"})
@RestController
@RequestMapping("/api/score")
@EnableSwagger2
public class scoreController {

    @ApiOperation(value = "获取用户详细信息", tags = {"成绩信息的接口文档"})
    @GetMapping("/test")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Score" + name;
    }

}
