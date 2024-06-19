package com.example.scoreservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@ApiModel(description = "赴校外交流")
public class exchange {
    private int PID;

    @ApiModelProperty(value = "学生学号", example = "21310000")
    private String SID;

    private String title;

    private int type;

    private String funding_source;

    private String country;

    private String city;

    private String institution;

    private int duration;

    private Date date_start;

    private Date date_end;

    private int current_status;

    private int flag1;

    private int flag2;

    private int flag3;

    private int flag4;

    private String link_name;

    @ApiModelProperty(value = "证明文件链接", example = "...")
    private String link;

    private String remarks;

    private int status_one;

    private int status_two;

    @ApiModelProperty(value = "审核建议", example = "...")
    private String comment;
}
