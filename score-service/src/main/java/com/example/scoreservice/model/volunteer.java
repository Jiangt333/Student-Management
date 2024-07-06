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
@ApiModel(description = "志愿服务活动")
public class volunteer {
    private int PID;

    @ApiModelProperty(value = "学生学号", example = "21310000")
    private String SID;

    @ApiModelProperty(value = "大序号", example = "1")
    private int idx;

    private float score;

    private String title;

    private String organization;

    private int type;

    @ApiModelProperty(value = "开始日期", example = "2024-06-15")
    private Date date_start;

    @ApiModelProperty(value = "结束日期", example = "2024-06-15")
    private Date date_end;

    private float duration;

    private String link_name;

    @ApiModelProperty(value = "证明文件链接", example = "...")
    private String link;

    private String remarks;

    private int status_one;

    private int status_two;

    @ApiModelProperty(value = "审核建议", example = "...")
    private String comment;
}
