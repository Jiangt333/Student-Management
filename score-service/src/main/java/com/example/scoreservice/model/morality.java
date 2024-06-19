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
@ApiModel(description = "政治思想道德")
public class morality {
    private int PID;

    @ApiModelProperty(value = "学生学号", example = "21310000")
    private String SID;

    @ApiModelProperty(value = "大序号", example = "1")
    private int idx;

    @ApiModelProperty(value = "加分", example = "0")
    private float score;

    private String title;

    @ApiModelProperty(value = "日期格式", example = "2024-06-15")
    private Date date;

    private String link_name;

    @ApiModelProperty(value = "证明文件链接", example = "...")
    private String link;

    private String remarks;

    private int status_one;

    private int status_two;

    @ApiModelProperty(value = "审核建议", example = "...")
    private String comment;
}
