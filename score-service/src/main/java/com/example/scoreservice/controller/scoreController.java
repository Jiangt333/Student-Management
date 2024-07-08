package com.example.scoreservice.controller;

import com.example.scoreservice.model.*;
import com.example.scoreservice.utils.Response;
import com.example.scoreservice.service.ScoreService;
import com.example.scoreservice.utils.myException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Map;

@Api(tags = {"综测信息的接口文档"})
@RestController
@RequestMapping("/score")
@EnableSwagger2
public class scoreController {

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private Response response;

    /**
     * 各表通用的接口
     */
    @ApiOperation(value = "各表通用：根据学生SID返回指定表的综测填报信息，其中table填写对应表名")
    @GetMapping("/common")
    @ResponseBody
    public <T> Response<List<T>> selectTable(@RequestParam String SID, @RequestParam String table) {
        try {
            Class<T> modelClass = (Class<T>) Class.forName("com.example.scoreservice.model." + table);
            List<T> list = scoreService.selectTable(SID, table, modelClass);
            response.data = list;
            response.code = 200;
        }
        catch (myException.NoMatchingRecordException e){
            response.data = "没有匹配的条目，请检查参数";
            response.code = 4005;
        }
        catch (Exception e) {
            e.printStackTrace();
            response.data = "sql错误或其他捕获异常！";
            response.code = 4004;
        }
        return response;
    }

    @ApiOperation(value = "获取文体比赛列表")
    @GetMapping("/artistic-competition")
    @ResponseBody
    public Response<List<competition>> getArtistCompetition() {
        response.data = scoreService.getCompetition(0);
        response.code = 200;
        return response;
    }

    @ApiOperation(value = "获取学科比赛列表")
    @GetMapping("/study-competition")
    @ResponseBody
    public Response<List<competition>> getStudyCompetition() {
        response.data = scoreService.getCompetition(1);
        response.code = 200;
        return response;
    }

    @ApiOperation(value = "各表通用：根据主键PID, 学生SID删除指定表的综测填报信息")
    @DeleteMapping("/common")
    @ResponseBody
    public Response<String> deleteTable(@RequestParam int PID, @RequestParam String SID, @RequestParam String table) {
        try{
            scoreService.deleteTable(PID, SID, table);
            response.data = "操作成功";
            response.code = 200;
        }
        catch (myException.NoMatchingRecordException e){
            response.data = "没有匹配的条目，请检查参数";
            response.code = 4005;
        }
        catch (Exception e){
            e.printStackTrace();
            response.data = "sql错误或其他捕获异常！";
            response.code = 4004;
        }
        return response;
    }

    @ApiOperation(value = "各表通用：根据PID更新idx和score")
    @PutMapping("/common/idx_score")
    @ResponseBody
    public Response<String> updateIdxAndScore(@RequestParam int PID, @RequestParam String table, @RequestParam int idx, @RequestParam float score) {
        if(scoreService.updateIdxAndScore(PID, table, idx, score)){
            response.data = "操作成功";
            response.code = 200;
        }
        else{
            response.data = "没有匹配的条目，请检查参数";
            response.code = 4005;
        }
        return response;
    }

    @ApiOperation(value = "各表通用：更新status_one")
    @PutMapping("/common/status_one")
    @ResponseBody
    public Response<String> updateStatusOne(@RequestParam int PID, @RequestParam String table, @RequestParam int status_one) {
        if(scoreService.updateStatusOne(PID, table, status_one)){
            response.data = "操作成功";
            response.code = 200;
        }
        else{
            response.data = "没有匹配的条目，请检查参数";
            response.code = 4005;
        }
        return response;
    }

    @ApiOperation(value = "各表通用：更新status_two")
    @PutMapping("/common/status_two")
    @ResponseBody
    public Response<String> updateStatusTwo(@RequestParam int PID, @RequestParam String SID, @RequestParam String table, @RequestParam int status_two) {
        if(scoreService.updateStatusTwo(PID, SID, table, status_two)){
            response.data = "操作成功";
            response.code = 200;
        }
        else{
            response.data = "没有匹配的条目，请检查参数";
            response.code = 4005;
        }
        return response;
    }

    @ApiOperation(value = "管理员一审接口")
    @PutMapping("/admin/status_one")
    @ResponseBody
    public Response<String> adminOne(@RequestParam Integer PID, @RequestParam String SID, @RequestParam String table, @RequestParam Integer status_one, @RequestParam String comment) {
        if(scoreService.verifyByAdmin(1, PID, SID, table, status_one, comment)){
            response.data = "操作成功";
            response.code = 200;
        }
        else{
            response.data = "没有匹配的条目，请检查参数";
            response.code = 4005;
        }
        return response;
    }

    @ApiOperation(value = "管理员二审接口")
    @PutMapping("/admin/status_two")
    @ResponseBody
    public Response<String> adminTwo(@RequestParam Integer PID, @RequestParam String SID, @RequestParam String table, @RequestParam Integer status_two, @RequestParam String comment) {
        if(scoreService.verifyByAdmin(2, PID, SID, table, status_two, comment)){
            response.data = "操作成功";
            response.code = 200;
        }
        else{
            response.data = "操作失败";
            response.code = 4005;
        }
        return response;
    }


//    @ApiOperation(value = "各表通用：提交填报某表的表单")
//    @PostMapping("/common")
//    @ResponseBody
//    public <T> Response<Integer> submitForm(@RequestBody T form, @RequestParam String table){
////            Class<T> formClass = (Class<T>) Class.forName("com.example.scoreservice.model." + table);
////            T castedForm = formClass.cast(form);
//        int result = scoreService.submitForm(form, table);
//        if (result == 1) {
//            response.data = "操作成功";
//            response.code = 200;
//        } else {
//            response.data = "出现异常，操作失败";
//            response.code = 4004;
//        }
//        return response;
//    }

    /**
     * morality
     */
    @ApiOperation(value = "提交填报政治思想道德表的表单，返回1提交成功，0提交失败")
    @PostMapping("/morality")
    public Response<Integer> submitMorality(@RequestBody morality moral) {
        if(scoreService.submitMorality(moral) == 1){
            response.data = 1;
            response.code = 200;
        }
        else{
            response.data = 0;
            response.code = 4004;
        }
        return response;
    }

    /**
     * volunteer
     */
    @ApiOperation(value = "提交填报志愿服务活动表的表单，返回1提交成功，0提交失败")
    @PostMapping("/volunteer")
    public Response<Integer> submitVolunteer(@RequestBody volunteer volun) {
        if(scoreService.submitVolunteer(volun) == 1){
            response.data = 1;
            response.code = 200;
        }
        else{
            response.data = 0;
            response.code = 4004;   // 失败规定的状态码
        }
        return response;
    }

    /**
     * socialwork
     */
    @ApiOperation(value = "提交填报社会工作表的表单，返回1提交成功，0提交失败")
    @PostMapping("/socialwork")
    public Response<Integer> submitSocialwork(@RequestBody socialwork soc) {
        if(scoreService.submitSocialwork(soc) == 1){
            response.data = 1;
            response.code = 200;
        }
        else{
            response.data = 0;
            response.code = 4004;   // 失败规定的状态码
        }
        return response;
    }

    /**
     * competition
     */
    @ApiOperation(value = "提交填报比赛获奖表的表单，返回1提交成功，0提交失败")
    @PostMapping("/competition")
    public Response<Integer> submitCompetition(@RequestBody competition com) {
        if(scoreService.submitCompetition(com) == 1){
            response.data = 1;
            response.code = 200;
        }
        else{
            response.data = 0;
            response.code = 4004;   // 失败规定的状态码
        }
        return response;
    }

    /**
     * paper
     */
    @ApiOperation(value = "提交填报论文发表表的表单，返回1提交成功，0提交失败")
    @PostMapping("/paper")
    public Response<Integer> submitPaper(@RequestBody paper pap) {
        if(scoreService.submitPaper(pap) == 1){
            response.data = 1;
            response.code = 200;
        }
        else{
            response.data = 0;
            response.code = 4004;   // 失败规定的状态码
        }
        return response;
    }

    /**
     * patent
     */
    @ApiOperation(value = "提交填报专利发明表的表单，返回1提交成功，0提交失败")
    @PostMapping("/patent")
    public Response<Integer> submitPatent(@RequestBody patent pat) {
        if(scoreService.submitPatent(pat) == 1){
            response.data = 1;
            response.code = 200;
        }
        else{
            response.data = 0;
            response.code = 4004;   // 失败规定的状态码
        }
        return response;
    }

    /**
     * copyright
     */
    @ApiOperation(value = "提交填报软件著作权发明表的表单，返回1提交成功，0提交失败")
    @PostMapping("/copyright")
    public Response<Integer> submitCopyright(@RequestBody copyright cop) {
        if(scoreService.submitCopyright(cop) == 1){
            response.data = 1;
            response.code = 200;
        }
        else{
            response.data = 0;
            response.code = 4004;   // 失败规定的状态码
        }
        return response;
    }

    /**
     * publication
     */
    @ApiOperation(value = "提交填报专著出版表的表单，返回1提交成功，0提交失败")
    @PostMapping("/publication")
    public Response<Integer> submitPublication(@RequestBody publication pub) {
        if(scoreService.submitPublication(pub) == 1){
            response.data = 1;
            response.code = 200;
        }
        else{
            response.data = 0;
            response.code = 4004;   // 失败规定的状态码
        }
        return response;
    }

    /**
     * exchange
     */
    @ApiOperation(value = "提交填报赴校外交流表的表单，返回1提交成功，0提交失败")
    @PostMapping("/exchange")
    public Response<Integer> submitExchange(@RequestBody exchange exch) {
        if(scoreService.submitExchange(exch) == 1){
            response.data = 1;
            response.code = 200;
        }
        else{
            response.data = 0;
            response.code = 4004;   // 失败规定的状态码
        }
        return response;
    }

}
