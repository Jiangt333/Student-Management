package com.example.authservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(description = "登录用户类")
public class SimpleUser {
    @ApiModelProperty(value = "学号", example = "21319743")
    private String SID;

    @ApiModelProperty(value = "姓名", example = "王凌")
    private String name;

    @ApiModelProperty(value = "密码", example = "123456")
    private String password;

//    private String email;
}
