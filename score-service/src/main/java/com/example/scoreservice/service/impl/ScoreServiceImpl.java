package com.example.scoreservice.service.impl;

import com.example.scoreservice.dao.ScoreDao;
import com.example.scoreservice.model.ScoreInfo;
import com.example.scoreservice.model.ScoreStandard;
import com.example.scoreservice.service.ScoreService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreDao scoreDao;

    @Override
    public List<ScoreStandard> selectPolitics(){
        List<ScoreStandard> list = scoreDao.selectPolitics();
        return list;
    }

    @Override
    public List<ScoreStandard> selectPractice(){
        List<ScoreStandard> list = scoreDao.selectPractice();
        return list;
    }

    @Override
    public List<ScoreStandard> selectSocialWork(){
        List<ScoreStandard> list = scoreDao.selectSocialWork();
        return list;
    }

    @Override
    public List<ScoreStandard> selectStudy(){
        List<ScoreStandard> list = scoreDao.selectStudy();
        return list;
    }


    @Override
    public int submitPolitics(ScoreInfo scoreinfo){
        try{
            scoreDao.submitPolitics(scoreinfo);
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int submitPractice(ScoreInfo scoreinfo){
        try{
            scoreDao.submitPractice(scoreinfo);
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int submitSocialWork(ScoreInfo scoreinfo){
        try{
            scoreDao.submitSocialWork(scoreinfo);
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int submitStudy(ScoreInfo scoreinfo){
        try{
            scoreDao.submitStudy(scoreinfo);
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<ScoreInfo> selectPoliticsRecords(String SID){
        List<ScoreInfo> list = scoreDao.selectPoliticsRecords(SID);
        return list;
    }

    @Override
    public List<ScoreInfo> selectPracticeRecords(String SID){
        List<ScoreInfo> list = scoreDao.selectPracticeRecords(SID);
        return list;
    }

    @Override
    public List<ScoreInfo> selectSocialWorkRecords(String SID){
        List<ScoreInfo> list = scoreDao.selectSocialWorkRecords(SID);
        return list;
    }

    @Override
    public List<ScoreInfo> selectStudyRecords(String SID){
        List<ScoreInfo> list = scoreDao.selectStudyRecords(SID);
        return list;
    }




}
