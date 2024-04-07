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
    private String SName;

    @ApiModelProperty(value = "密码", example = "123456")
    private String SPassword;

    @ApiModelProperty(value = "学生类别", example = "境内生")
    private Integer SCategory;

    @ApiModelProperty(value = "身份证号", example = "450287200305242459")
    private String SIDNum;
}
