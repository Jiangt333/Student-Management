package com.example.authservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@ApiModel(description = "邮箱验证类")
public class MailCode {

    private String toMail;

    private String code;

    private Date start_time;

}
