package com.example.scoreservice.service.impl;

import com.example.scoreservice.dao.ScoreDao;
import com.example.scoreservice.model.*;
import com.example.scoreservice.service.ScoreService;
import com.example.scoreservice.utils.myException;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanUtils;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreDao scoreDao;

    @Override
    public <T> List<T> selectTable(String SID, String table, Class<T> type) throws myException.SqlOperationException, myException.NoMatchingRecordException {
        try {
            List<Map<String, Object>> result = scoreDao.selectTable(SID, table);
            if(result.isEmpty()){
                throw new myException.NoMatchingRecordException("No matching record found for operation");
            }
            List<T> typedResult = new ArrayList<>();
            for (Map<String, Object> map : result) {
                T instance = type.newInstance();
                BeanUtils.populate(instance, map);
                typedResult.add(instance);
            }
            return typedResult;
        }
        catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new myException.SqlOperationException("Error instantiating or accessing class: " + e.getMessage());
        }
    }

    @Override
    public List<competition> getCompetition(int type){
        if(type != 1){
            return scoreDao.getArtisticCompeition();
        }
        return scoreDao.getStudyCompeition();
    }

    @Override
    public List<Map<String, Object>> selectScoreAndDate(int PID, String table) {
        return scoreDao.selectScoreAndDate(PID, table);
    }

    @Override
    public int deleteTable(int PID, String SID, String table) throws myException.SqlOperationException, myException.NoMatchingRecordException {
        int result = scoreDao.deleteTable(PID, SID, table);
        if (result == 0) {
            throw new myException.NoMatchingRecordException("No matching record found for operation");
        }
        return result;
    }

    @Override
    public boolean updateIdxAndScore(int PID, String table, int idx, float score){
        int result = scoreDao.updateIdxAndScore(PID, table, idx, score);
        if (result == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateStatusOne(int PID, String table, int status_one){
        int result = scoreDao.updateStatusOne(PID, table, status_one);
        if (result == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateStatusTwo(int PID, String table, int status_two){
        int result = scoreDao.updateStatusTwo(PID, table, status_two);
        if (result == 0) {
            return false;
        }
        return true;
    }

//    @Override
//     public int updateGpa(String SID, float score, Date date){
//        int re = scoreDao.updateGpa(SID, score, date);
//    }

//
//    @Override
//    public <T> int submitForm(T form, String type) {
//        switch (type) {
//            case "morality":
//                return submitMorality((morality) form);
//            case "volunteer":
//                return submitVolunteer((volunteer) form);
//            case "socialwork":
//                return submitSocialwork((socialwork) form);
//            case "competition":
//                return submitCompetition((competition) form);
//            case "paper":
//                return submitPaper((paper) form);
//            case "patent":
//                return submitPatent((patent) form);
//            case "copyright":
//                return submitCopyright((copyright) form);
//            case "publication":
//                return submitPublication((publication) form);
//            case "exchange":
//                return submitExchange((exchange) form);
//            default:
//                throw new IllegalArgumentException("Unknown form type: " + type);
//        }
//    }

    @Override
    public boolean verifyByAdmin(Integer num, Integer PID, String table, Integer status_one, String comment){
        boolean flag = false;
        if(num == 1){
            // 一审的逻辑：直接更新status_one 和 comment
            if(scoreDao.updateStatusOneByAdmin(PID, table, status_one, comment) == 1)
                flag = true;
            flag = false;
        }
        else{
            // 二审的逻辑
            // 1. 更新status_two 和 comment
            if(scoreDao.updateStatusOneByAdmin(PID, table, status_one, comment) == 1){
                // 2. 如果status_two=1，累计score到gpa的对应类里

            }
        }
        return flag;
    }

    /**
     * morality
     */
    @Override
    public int submitMorality(morality moral){
        try{
            scoreDao.submitMorality(moral);
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * volunteer
     */
    @Override
    public int submitVolunteer(volunteer volun){
        try{
            scoreDao.submitVolunteer(volun);
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * socialwork
     */
    @Override
    public int submitSocialwork(socialwork soc){
        try{
            scoreDao.submitSocialwork(soc);
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * competition
     */
    @Override
    public int submitCompetition(competition com){
        try{
            scoreDao.submitCompetition(com);
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * paper
     */
    @Override
    public int submitPaper(paper pap){
        try{
            scoreDao.submitPaper(pap);
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * patent
     */
    @Override
    public int submitPatent(patent pat){
        try{
            scoreDao.submitPatent(pat);
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * copyright
     */
    @Override
    public int submitCopyright(copyright cop){
        try{
            scoreDao.submitCopyright(cop);
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * publication
     */
    @Override
    public int submitPublication(publication pub){
        try{
            scoreDao.submitPublication(pub);
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * exchange
     */
    @Override
    public int submitExchange(exchange exch){
        try{
            scoreDao.submitExchange(exch);
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
