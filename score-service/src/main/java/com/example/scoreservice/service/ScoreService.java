package com.example.scoreservice.service;

import com.example.scoreservice.model.*;
import com.example.scoreservice.utils.myException;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ScoreService {

    <T> List<T> selectTable(String SID, String table, Class<T> type) throws myException.SqlOperationException, myException.NoMatchingRecordException;

    List<Map<String, Object>> selectScoreAndDate(int PID, String table);

    int deleteTable(int PID, String SID, String table) throws myException.SqlOperationException, myException.NoMatchingRecordException;

    boolean updateIdxAndScore(int PID, String SID, int idx, float score);

    boolean updateStatusOne(int PID, String table, int status_one);

    boolean updateStatusTwo(int PID, String table, int status_two);

//    int updateGpa(String SID, float score, Date date);


//    public <T> int submitForm(T form, String type);

    /**
     * morality
     */
    int submitMorality(morality moral);

    /**
     * volunteer
     */
    int submitVolunteer(volunteer volun);

    /**
     * socialwork
     */
    int submitSocialwork(socialwork soc);

    /**
     * competition
     */
    int submitCompetition(competition com);

    /**
     * paper
     */
    int submitPaper(paper pap);

    /**
     * patent
     */
    int submitPatent(patent pat);

    /**
     * copyright
     */
    int submitCopyright(copyright cop);

    /**
     * publication
     */
    int submitPublication(publication pub);

    /**
     * exchange
     */
    int submitExchange(exchange exch);

}
