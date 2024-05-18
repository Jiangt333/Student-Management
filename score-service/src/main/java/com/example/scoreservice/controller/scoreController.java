package com.example.scoreservice.controller;


import com.example.authservice.utils.Response;
import com.example.scoreservice.model.ScoreInfo;
import com.example.scoreservice.model.ScoreStandard;
import com.example.scoreservice.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Api(tags = {"综测信息的接口文档"})
@RestController
@RequestMapping("/score")
@EnableSwagger2
public class scoreController {

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private Response response;

    @ApiOperation(value = "返回politics_standard表综测具体条目及对应分值")
    @GetMapping("/politics-standard")
    @ResponseBody
    public Response<List<ScoreStandard>> selectPolitics() {
        List<ScoreStandard> list = scoreService.selectPolitics();
        response.data = list;
        return response;
    }

    @ApiOperation(value = "返回practice_standard表综测具体条目及对应分值")
    @GetMapping("/practice-standard")
    @ResponseBody
    public Response<List<ScoreStandard>> selectPractice() {
        List<ScoreStandard> list = scoreService.selectPractice();
        response.data = list;
        return response;
    }

    @ApiOperation(value = "返回socialwork_standard表综测具体条目及对应分值")
    @GetMapping("/socialwork-standard")
    @ResponseBody
    public Response<List<ScoreStandard>> selectSocialWork() {
        List<ScoreStandard> list = scoreService.selectSocialWork();
        response.data = list;
        return response;
    }

    @ApiOperation(value = "返回study_standard表综测具体条目及对应分值")
    @GetMapping("/study-standard")
    @ResponseBody
    public Response<List<ScoreStandard>> selectStudy() {
        List<ScoreStandard> list = scoreService.selectStudy();
        response.data = list;
        return response;
    }

    @ApiOperation(value = "提交综测填报信息某表的表单，返回1提交成功，0提交失败")
    @PostMapping("/politics")
    public Response<Integer> submitPolitics(@RequestBody ScoreInfo scoreinfo) {
        if(scoreService.submitPolitics(scoreinfo) == 1){
            response.data = 1;
        }
        else{
            response.data = 0;
            response.code = 4006;   // 插入失败规定的状态码
        }
        return response;
    }

    @ApiOperation(value = "提交综测填报信息某表的表单，返回1提交成功，0提交失败")
    @PostMapping("/practice")
    public Response<Integer> submitPractice(@RequestBody ScoreInfo scoreinfo) {
        if(scoreService.submitPractice(scoreinfo) == 1){
            response.data = 1;
        }
        else{
            response.data = 0;
            response.code = 4006;   // 插入失败规定的状态码
        }
        return response;
    }

    @ApiOperation(value = "提交综测填报信息某表的表单，返回1提交成功，0提交失败")
    @PostMapping("/socialWork")
    public Response<Integer> submitSocialWork(@RequestBody ScoreInfo scoreinfo) {
        if(scoreService.submitSocialWork(scoreinfo) == 1){
            response.data = 1;
        }
        else{
            response.data = 0;
            response.code = 4006;   // 插入失败规定的状态码
        }
        return response;
    }

    @ApiOperation(value = "提交综测填报信息某表的表单，返回1提交成功，0提交失败")
    @PostMapping("/study")
    public Response<Integer> submitStudy(@RequestBody ScoreInfo scoreinfo) {
        if(scoreService.submitStudy(scoreinfo) == 1){
            response.data = 1;
        }
        else{
            response.data = 0;
            response.code = 4006;   // 插入失败规定的状态码
        }
        return response;
    }

    @ApiOperation(value = "根据学生id返回politics表的综测填报信息")
    @GetMapping("/politicsRecords")
    @ResponseBody
    public Response<List<ScoreInfo>> selectPoliticsRecords(@RequestParam String SID) {
        List<ScoreInfo> list = scoreService.selectPoliticsRecords(SID);
        response.data = list;
        return response;
    }

    @ApiOperation(value = "根据学生id返回practice表的综测填报信息")
    @GetMapping("/practiceRecords")
    @ResponseBody
    public Response<List<ScoreInfo>> selectPracticeRecords(@RequestParam String SID) {
        List<ScoreInfo> list = scoreService.selectPracticeRecords(SID);
        response.data = list;
        return response;
    }

    @ApiOperation(value = "根据学生id返回socialwork表的综测填报信息")
    @GetMapping("/socialWorkRecords")
    @ResponseBody
    public Response<List<ScoreInfo>> selectSocialWorkRecords(@RequestParam String SID) {
        List<ScoreInfo> list = scoreService.selectSocialWorkRecords(SID);
        response.data = list;
        return response;
    }

    @ApiOperation(value = "根据学生id返回study表的综测填报信息")
    @GetMapping("/studyRecords")
    @ResponseBody
    public Response<List<ScoreInfo>> selectStudyRecords(@RequestParam String SID) {
        List<ScoreInfo> list = scoreService.selectStudyRecords(SID);
        response.data = list;
        return response;
    }
}
