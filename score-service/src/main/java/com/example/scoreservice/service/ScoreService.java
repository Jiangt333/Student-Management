package com.example.scoreservice.service;

import com.example.scoreservice.model.*;
import com.example.scoreservice.utils.myException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreService {

    <T> List<T> selectTable(String SID, String table, Class<T> type) throws myException.SqlOperationException, myException.NoMatchingRecordException;

    int deleteTable(String PID, String SID, String table) throws myException.SqlOperationException, myException.NoMatchingRecordException;

    boolean updateStatusOne(String PID, String table, int status_one);

    boolean updateStatusTwo(String PID, String table, int status_two);

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
