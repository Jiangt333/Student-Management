package com.example.userservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@ApiModel(description = "用户类")
public class User {
    @ApiModelProperty(value = "学号", example = "21319743")
    private String SID;

    @ApiModelProperty(value = "姓名", example = "王凌")
    private String SName;

    @ApiModelProperty(value = "密码", example = "123456")
    private String SPassword;

    @ApiModelProperty(value = "学生类别", example = "境内生")
    private Integer SCategory;

    @ApiModelProperty(value = "性别", example = "男")
    private Integer SGender;

    @ApiModelProperty(value = "民族", example = "汉族")
    private Integer SNation;

    @ApiModelProperty(value = "身份证号", example = "450287200305242459")
    private String SIDNum;

    @ApiModelProperty(value = "出生日期", example = "2004-10-08")
    private Date SYear;

    @ApiModelProperty(value = "班级", example = "21级2班")
    private Integer SClass;

    @ApiModelProperty(value = "培养层次", example = "本科生")
    private Integer SLevel;

    @ApiModelProperty(value = "政治面貌", example = "共青团员")
    private Integer SOutlook;

    @ApiModelProperty(value = "宿舍", example = "榕园18栋620室")
    private String SDorm;

    @ApiModelProperty(value = "籍贯", example = "福建省厦门市")
    private String SPlace;

    @ApiModelProperty(value = "联系方式", example = "18898702316")
    private String SPhone;

    @ApiModelProperty(value = "详细住址", example = "思明区嘉莲大街78-982")
    private String SHome;

    @ApiModelProperty(value = "紧急联系人姓名", example = "王褚圣")
    private String ContactName;

    @ApiModelProperty(value = "紧急联系人电话", example = "13899021243")
    private String ContactPhone;

    @ApiModelProperty(value = "微信", example = "WangLin6")
    private String SWechat;

    @ApiModelProperty(value = "邮箱", example = "wanglin6@mail2.sysu.edu.cn")
    private String SMail;
}
