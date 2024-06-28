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
@ApiModel(description = "专利发明")
public class patent {
    private int PID;

    @ApiModelProperty(value = "学生学号", example = "21310000")
    private String SID;

    @ApiModelProperty(value = "大序号", example = "1")
    private int idx;

    @ApiModelProperty(value = "加分", example = "0")
    private float score;

    private String title;

    private int type;

    private String application_num;

    private String certificate_num;

    private String team;

    private int acceptance;

    private Date acceptance_date;

    private int my_release;

    private Date release_date;

    private int empower;

    private Date empower_date;

    private int transferred;

    private Date transferred_date;

    private float transferred_income;

    private String link_name;

    @ApiModelProperty(value = "证明文件链接", example = "...")
    private String link;

    private String remarks;

    private int status_one;

    private int status_two;

    @ApiModelProperty(value = "审核建议", example = "...")
    private String comment;

}
