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
@ApiModel(description = "论文发表")
public class paper {
    private int PID;

    @ApiModelProperty(value = "学生学号", example = "21310000")
    private String SID;

    @ApiModelProperty(value = "大序号", example = "1")
    private int idx;

    @ApiModelProperty(value = "加分", example = "0")
    private float score;

    private String title;

    private int type;

    private String publish;

    private int author_level;

    private String authors;

    private String corresponding_author;

    private String ISSN_CN;

    private float factor;

    private int published_status;

    private Date submission_date;

    private Date received_date;

    private Date publication_date;

    private String my_range;

    private String DOI_PMID;

    private String CCF;

    private int my_partition;

    private int inclusion;

    private String publisher;

    private String language;

    private int award_flag;

    private int collaborative_one;

    private int collaborative_two;

    private String link_name;

    @ApiModelProperty(value = "证明文件链接", example = "...")
    private String link;

    private String remarks;

    private int status_one;

    private int status_two;

    @ApiModelProperty(value = "审核建议", example = "...")
    private String comment;
}
