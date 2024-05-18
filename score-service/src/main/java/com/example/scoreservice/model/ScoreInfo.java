package com.example.scoreservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(description = "学生填的综测信息")
public class ScoreInfo {
    @ApiModelProperty(value = "自增主键", example = "1")
    private int PID;

    @ApiModelProperty(value = "学生学号", example = "21310000")
    private String SID;

    @ApiModelProperty(value = "大序号", example = "1")
    private int idx;

    @ApiModelProperty(value = "综测加分条目具体内容", example = "某比赛多少名")
    private String reward;

    @ApiModelProperty(value = "加分", example = "0.5")
    private float score;

    @ApiModelProperty(value = "证明文件链接", example = "...")
    private String link;

    @ApiModelProperty(value = "审核建议", example = "...")
    private String comment;

    @ApiModelProperty(value = "状态", example = "1")
    private int status;
}
