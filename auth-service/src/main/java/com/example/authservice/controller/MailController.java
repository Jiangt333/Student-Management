package com.example.authservice.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Api(tags = {"邮箱验证文档"})
@RestController
@RequestMapping("/mail")
@EnableSwagger2
public class MailController {

}
