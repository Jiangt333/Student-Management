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
import org.springframework.transaction.annotation.Transactional;


import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Transactional
    public boolean updateStatusTwo(int PID, String SID, String table, int status_two){
        int result = scoreDao.updateStatusTwo(PID, SID, table, status_two);
        int re = scoreDao.addOverall(SID);
        if (result == 0 || re == 0) {
            return false;
        }
        return true;
    }

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
    @Transactional
    public boolean verifyByAdmin(Integer num, Integer PID, String SID, String table, Integer status, String comment){
        boolean flag = false;
        if(num == 1){
            // 一审的逻辑：直接更新status_one 和 comment
            scoreDao.subPersonal(SID);
            if(scoreDao.updateStatusOneByAdmin(PID, table, status, comment) == 1)
                flag = true;
            else
                flag = false;
        }
        else {
            // 二审的逻辑
            // 1. 更新status_two 和 comment
            scoreDao.subOverall(SID);
            if(scoreDao.updateStatusTwoByAdmin(PID, table, status, comment) == 1){
                // 2. 如果status_two=1，累计score到gpa的对应类里
                if(status == 1 && table != "exchange"){
                    Map<String, Object> mp = scoreDao.selectItem(PID, table);
                    System.err.println(mp);
                    float score = ((Number) mp.get("score")).floatValue();
                    Date date;
                    if (table.equals("competition")) {
                        int type = ((Number) mp.get("type")).intValue();
                        if (type == 1)
                            table = "study_competition";
                        else
                            table = "art_competition";
                        date = parseDate(mp.get("date"));
                    } else if (table.equals("paper")) {
                        date = parseDate(mp.get("publication_date"));
                    } else if (table.equals("patent")) {
                        date = parseDate(mp.get("acceptance_date"));
                    } else if (table.equals("socialwork")) {
                        date = parseDate(mp.get("date_start"));
                    } else if (table.equals("volunteer")) {
                        date = parseDate(mp.get("date_start"));
                    }else {
                        date = parseDate(mp.get("date"));
                    }
//                    if(table == "competition"){
//                        int type = ((Number) mp.get("type")).intValue();
//                        if(type == 1)
//                            table = "study_competition";
//                        else
//                            table = "art_competition";
//                        date = (Date) mp.get("date");
//                    } else if(table == "paper"){
//                        date = (Date) mp.get("publication_date");
//                    } else if(table == "patent"){
//                        date = (Date) mp.get("acceptance_date");
//                    } else if(table == "socialwork"){
//                        date = (Date) mp.get("date_start");
//                    } else{
//                        date = (Date) mp.get("date");
//                    }
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    if (month >= Calendar.SEPTEMBER) {
                        year += 1;
                    }

                    if(scoreDao.selectGpa(SID, year) == 0){
                        scoreDao.insertNewGpa(SID, year);
                        System.err.println("insert!");
                    }
                    int re = scoreDao.updateGpa(table, SID, score, year);
                    System.err.println(year);
                    System.err.println(SID);
                    System.err.println(score);
                    System.err.println("update!");
                    if(re == 1)
                        flag = true;
                    else
                        flag = false;
                }
                else{
                    flag = true;
                }
            }
        }
        return flag;
    }

    private Date parseDate(Object dateObj) {
        if (dateObj == null) {
            return null;
        } else if (dateObj instanceof Date) {
            return (Date) dateObj;
        } else if (dateObj instanceof String) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse((String) dateObj);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * morality
     */
    @Override
    @Transactional
    public int submitMorality(morality moral){
        try{
            scoreDao.submitMorality(moral);
            scoreDao.addPersonal(moral.getSID());
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
    @Transactional
    public int submitVolunteer(volunteer volun){
        try{
            scoreDao.submitVolunteer(volun);
            scoreDao.addPersonal(volun.getSID());
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
    @Transactional
    public int submitSocialwork(socialwork soc){
        try{
            scoreDao.submitSocialwork(soc);
            scoreDao.addPersonal(soc.getSID());
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
    @Transactional
    public int submitCompetition(competition com){
        try{
            scoreDao.submitCompetition(com);
            scoreDao.addPersonal(com.getSID());
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
    @Transactional
    public int submitPaper(paper pap){
        try{
            scoreDao.submitPaper(pap);
            scoreDao.addPersonal(pap.getSID());
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
    @Transactional
    public int submitPatent(patent pat){
        try{
            scoreDao.submitPatent(pat);
            scoreDao.addPersonal(pat.getSID());
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
    @Transactional
    public int submitCopyright(copyright cop){
        try{
            scoreDao.submitCopyright(cop);
            scoreDao.addPersonal(cop.getSID());
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
    @Transactional
    public int submitPublication(publication pub){
        try{
            scoreDao.submitPublication(pub);
            scoreDao.addPersonal(pub.getSID());
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
    @Transactional
    public int submitExchange(exchange exch){
        try{
            scoreDao.submitExchange(exch);
            scoreDao.addPersonal(exch.getSID());
            return 1;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
