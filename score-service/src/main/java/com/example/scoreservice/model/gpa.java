package com.example.scoreservice.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class gpa {
    private int PID;

    private String SID;

    @ApiModelProperty(value = "学年", example = "2024")
    private String sch_year;

    @ApiModelProperty(value = "平均学分绩点", example = "4.5")
    private float gpa;

    @ApiModelProperty(value = "绩点排名", example = "1")
    private int gpa_rk;

    @ApiModelProperty(value = "综测加分", example = "2.1")
    private float com_bonus;

    @ApiModelProperty(value = "综测分数", example = "3.5")
    private float com_score;

    @ApiModelProperty(value = "综测排名", example = "1")
    private int com_rk;
}
