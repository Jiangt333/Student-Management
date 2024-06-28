package com.example.userservice.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@ApiModel(description = "用户类")
public class User {
    private int PID;

    private String SID;

    private String name;

    private String password;

    private Integer type;

    private Integer nation;

    private String identity;

    private Date birthday;

    private Integer level;

    private String grade;

    private String user_class;

    private Integer outlook;

    private String dorm;

    private String user_native;

    private String tel;

    private String wechat;

    private String email;

    private String address;

    private String emergency_name;

    private String emergency_tel;

    private Integer status;
}
