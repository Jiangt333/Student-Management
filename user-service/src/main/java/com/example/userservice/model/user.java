package com.example.userservice.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(description = "用户类")
public class user {

    private String name;

    private Integer age;

}
