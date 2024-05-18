package com.example.scoreservice.service;

import com.example.scoreservice.model.ScoreInfo;
import com.example.scoreservice.model.ScoreStandard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreService {
    List<ScoreStandard> selectPolitics();
    List<ScoreStandard> selectPractice();
    List<ScoreStandard> selectSocialWork();
    List<ScoreStandard> selectStudy();

    int submitPolitics(ScoreInfo scoreinfo);
    int submitPractice(ScoreInfo scoreinfo);
    int submitSocialWork(ScoreInfo scoreinfo);
    int submitStudy(ScoreInfo scoreinfo);

    List<ScoreInfo> selectPoliticsRecords(@Param("SID") String SID);
    List<ScoreInfo> selectPracticeRecords(@Param("SID") String SID);
    List<ScoreInfo> selectSocialWorkRecords(@Param("SID") String SID);
    List<ScoreInfo> selectStudyRecords(@Param("SID") String SID);
}
