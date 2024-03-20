package com.example.userservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(description = "用户类")
public class user {
//    @ApiModelProperty(value = "用户姓名", example = "江婷")
    private String name;
//    @ApiModelProperty(value = "用户年龄", example = "21")
    private Integer age;

}
