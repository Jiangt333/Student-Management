package com.example.scoreservice.dao;

import com.example.scoreservice.model.ScoreInfo;
import com.example.scoreservice.model.ScoreStandard;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface ScoreDao {
    /**
     * 返回某表综测具体条目及对应分值
     * 参数：无参
     * 返回：某表综测具体条目及对应分值
     */
    @Select("select * from politics_standard")
    List<ScoreStandard> selectPolitics();

    @Select("select * from practice_standard")
    List<ScoreStandard> selectPractice();

    @Select("select * from socialwork_standard")
    List<ScoreStandard> selectSocialWork();

    @Select("select * from study_standard")
    List<ScoreStandard> selectStudy();

    /**
     * 提交综测填报信息某表的表单
     * 参数：params（该表除主键外的所有字段）
     * 返回：
     */
    @Insert("insert into politics(SID, idx, reward, score, link, comment, status) " +
            "values(#{SID}, #{idx}, #{reward}, #{score}, #{link}, #{comment}, #{status})")
    void submitPolitics(ScoreInfo scoreinfo);

    @Insert("insert into practice(SID, idx, reward, score, link, comment, status) " +
            "values(#{SID}, #{idx}, #{reward}, #{score}, #{link}, #{comment}, #{status})")
    void submitPractice(ScoreInfo scoreinfo);

    @Insert("insert into socialwork(SID, idx, reward, score, link, comment, status) " +
            "values(#{SID}, #{idx}, #{reward}, #{score}, #{link}, #{comment}, #{status})")
    void submitSocialWork(ScoreInfo scoreinfo);

    @Insert("insert into study(SID, idx, reward, score, link, comment, status) " +
            "values(#{SID}, #{idx}, #{reward}, #{score}, #{link}, #{comment}, #{status})")
    void submitStudy(ScoreInfo scoreinfo);

    /**
     * 根据学生id返回某表的综测填报信息
     * 参数：params（SID）
     * 返回：相关填报信息的列表
     */
    @Select("select * from politics where SID = #{SID}")
    List<ScoreInfo> selectPoliticsRecords(@Param("SID") String SID);

    @Select("select * from practice where SID = #{SID}")
    List<ScoreInfo> selectPracticeRecords(@Param("SID") String SID);

    @Select("select * from socialwork where SID = #{SID}")
    List<ScoreInfo> selectSocialWorkRecords(@Param("SID") String SID);

    @Select("select * from study where SID = #{SID}")
    List<ScoreInfo> selectStudyRecords(@Param("SID") String SID);
}
