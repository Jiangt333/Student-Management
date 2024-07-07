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

    @ApiModelProperty(value = "志愿服务（0.75分）", example = "2.1")
    private float com_bonus_vol;

    @ApiModelProperty(value = "政治思想道德类（3分）", example = "2.1")
    private float com_bonus1;

    @ApiModelProperty(value = "社会工作类（3分）", example = "2.1")
    private float com_bonus2;

    @ApiModelProperty(value = "文体、实践类（4分）", example = "2.1")
    private float com_bonus3;

    @ApiModelProperty(value = "学习、竞赛及科研成果（6分）", example = "2.1")
    private float com_bonus4;

    @ApiModelProperty(value = "综测总加分（不超过gpa值的20%）", example = "2.1")
    private float com_bonus_total;

    @ApiModelProperty(value = "综测分数 = gpa + 10% * 综测总加分", example = "3.5")
    private float com_score;

    @ApiModelProperty(value = "综测排名", example = "1")
    private int com_rk;
}
