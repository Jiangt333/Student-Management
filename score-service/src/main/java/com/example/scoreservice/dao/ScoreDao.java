package com.example.scoreservice.dao;

import com.example.scoreservice.model.*;
import com.example.scoreservice.utils.SqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface ScoreDao {
    //    @Select("select * from #{table} where SID = #{SID}")
    //    List<Map<String, Object>> selectTable(@Param("SID") String SID, @Param("table") String table) throws PersistenceException;
    @SelectProvider(type = SqlProvider.class, method = "selectTable")
    List<Map<String, Object>> selectTable(@Param("SID") String SID, @Param("table") String table);

    @SelectProvider(type = SqlProvider.class, method = "selectScoreAndDate")
    List<Map<String, Object>> selectScoreAndDate(@Param("PID") int PID, @Param("table") String table);

    @DeleteProvider(type = SqlProvider.class, method = "deleteTable")
    int deleteTable(@Param("PID") int PID, @Param("SID") String SID, @Param("table") String table);

    @UpdateProvider(type = SqlProvider.class, method = "updateIdxAndScore")
    int updateIdxAndScore(@Param("PID") int PID, @Param("table") String table, @Param("idx") int idx, @Param("score") float score);

    @UpdateProvider(type = SqlProvider.class, method = "updateStatusOne")
    int updateStatusOne(@Param("PID") int PID, @Param("table") String table, @Param("status_one") int status_one);

    @UpdateProvider(type = SqlProvider.class, method = "updateStatusTwo")
    int updateStatusTwo(@Param("PID") int PID, @Param("table") String table, @Param("status_two") int status_two);

//    @Update("UPDATE gpa " +
//            "SET com_bonus = (CASE " +
//            "  WHEN com_bonus + #{score} > 5 THEN 5 " +
//            "  ELSE com_bonus + #{score} " +
//            "END) " +
//            "WHERE SID = #{SID} " +
//            "AND sch_year = (CASE " +
//            "  WHEN MONTH(date) >= 9 THEN YEAR(date) + 1 " +
//            "  ELSE YEAR(date) " +
//            "END)")
//    int updateGpa(@Param("SID") String SID, @Param("score") float score, @Param("Date") Date date);

    /**
     * morality
     */
    @Insert("insert into morality(SID, idx, score, title, date, link_name, link, remarks, status_one, status_two, comment) " +
            "values(#{SID}, #{idx}, #{score}, #{title}, #{date}, #{link_name}, #{link}, #{remarks}, 0, -1, #{comment})")
    int submitMorality(morality moral);

    /**
     * volunteer
     */
    @Insert("insert into volunteer(SID, idx, title, organization, type, date_start, date_end, duration, link_name, link, remarks, status_one, status_two, comment) " +
            "values(#{SID}, #{idx}, #{title}, #{organization}, #{type}, #{date_start}, #{date_end}, #{duration}, #{link_name}, #{link}, #{remarks}, 0, -1, #{comment})")
    int submitVolunteer(volunteer volun);

    /**
     * socialwork
     */
    @Insert("insert into socialwork(SID, idx, score, title, date_start, date_end, link_name, link, remarks, status_one, status_two, comment) " +
            "values(#{SID}, #{idx}, #{score}, #{title}, #{date_start}, #{date_end}, #{link_name}, #{link}, #{remarks}, 0, -1, #{comment})")
    int submitSocialwork(socialwork soc);

    /**
     * competition
     */
    @Insert("insert into competition(SID, idx, score, type, name, organization, date, title, level, my_rank, team, link_name, link, remarks, status_one, status_two, comment) " +
            "values(#{SID}, #{idx}, #{score}, #{type}, #{name}, #{organization}, #{date}, #{title}, #{level}, #{my_rank}, #{team}, #{link_name}, #{link}, #{remarks}, 0, -1, #{comment})")
    int submitCompetition(competition com);

    /**
     * paper
     */
    @Insert("insert into paper(SID, idx, score, title, type, publish, author_level, authors, corresponding_author, ISSN_CN, factor, published_status," +
            "submission_date, received_date, publication_date, my_range, DOI_PMID, CCF, my_partition, inclusion, publisher, language, award_flag, " +
            "collaborative_one, collaborative_two, link_name, link, remarks, status_one, status_two, comment) " +
            "values(#{SID}, #{idx}, #{score}, #{title}, #{type}, #{publish}, #{author_level}, #{authors}, #{corresponding_author}, #{ISSN_CN}, #{factor}, " +
            "#{published_status}, #{submission_date}, #{received_date}, #{publication_date}, #{my_range}, #{DOI_PMID}, #{CCF}, #{my_partition}, #{inclusion}, " +
            "#{publisher}, #{language}, #{award_flag}, #{collaborative_one}, #{collaborative_two}, " +
            "#{link_name}, #{link}, #{remarks}, 0, -1, #{comment})")
    int submitPaper(paper pap);

    /**
     * patent
     */
    @Insert("insert into patent(SID, idx, score, title, type, application_num, certificate_num, team, acceptance, acceptance_date, my_release, release_date," +
            "empower, empower_date, transferred, transferred_date, transferred_income, link_name, link, remarks, status_one, status_two, comment)" +
            "values(#{SID}, #{idx}, #{score}, #{title}, #{type}, #{application_num}, #{certificate_num}, #{team}, #{acceptance}, #{acceptance_date}, " +
            "#{my_release}, #{release_date}, #{empower}, #{empower_date}, #{transferred}, #{transferred_date}, #{transferred_income}," +
            "#{link_name}, #{link}, #{remarks}, 0, -1, #{comment})")
    int submitPatent(patent pat);

    /**
     * copyright
     */
    @Insert("insert into copyright(SID, idx, score, title, author_level, team, application_status, date, " +
            "link_name, link, remarks, status_one, status_two, comment)" +
            "values(#{SID}, #{idx}, #{score}, #{title}, #{author_level}, #{team}, #{application_status}, #{date}, " +
            "#{link_name}, #{link}, #{remarks}, 0, -1, #{comment})")
    int submitCopyright(copyright cop);

    /**
     * publication
     */
    @Insert("insert into publication(SID, idx, score, title, author_level, team, publisher, type, date, ISBN, " +
            "link_name, link, remarks, status_one, status_two, comment)" +
            "values(#{SID}, #{idx}, #{score}, #{title}, #{author_level}, #{team}, #{publisher}, #{type}, #{date}, #{ISBN}, " +
            "#{link_name}, #{link}, #{remarks}, 0, -1, #{comment})")
    int submitPublication(publication pub);

    /**
     * exchange
     */
    @Insert("insert into exchange(SID, title, type, funding_source, country, city, institution, duration, date_start, date_end, current_status," +
            "flag1, flag2, flag3, flag4, link_name, link, remarks, status_one, status_two, comment)" +
            "values(#{SID}, #{title}, #{type}, #{funding_source}, #{country}, #{city}, #{institution}, #{duration}, #{date_start}, #{date_end}, " +
            "#{current_status}, #{flag1}, #{flag2}, #{flag3}, #{flag4}, " +
            "#{link_name}, #{link}, #{remarks}, 0, -1, #{comment})")
    int submitExchange(exchange exch);
}
