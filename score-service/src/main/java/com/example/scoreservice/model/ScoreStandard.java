package com.example.scoreservice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@ApiModel(description = "综测具体条目及对应分值")
public class ScoreStandard {
    @ApiModelProperty(value = "大序号", example = "1")
    private int idx;

    @ApiModelProperty(value = "大序号下的子序号", example = "1")
    private int subidx;

    @ApiModelProperty(value = "综测加分条目具体内容", example = "某比赛多少名")
    private String reward;

    @ApiModelProperty(value = "加分", example = "0.5")
    private float score;
}
