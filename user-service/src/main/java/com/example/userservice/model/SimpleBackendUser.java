package com.example.userservice.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@ApiModel(description = "后台用户简单类")
public class SimpleBackendUser {
    private String SID;

    private String password;

    private String name;

    private Integer role;

    private String grade;

    private String user_class;
}
