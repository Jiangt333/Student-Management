package com.example.userservice.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@ApiModel(description = "后台用户类")
public class BackendUser {
    private int PID;

    private String SID;

//    private String password;

    private String name;

    private Integer role;

    private String grade;

    private String user_class;

    private Date create_time;
}
