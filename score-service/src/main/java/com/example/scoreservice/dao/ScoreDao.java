package com.example.scoreservice.dao;

import com.example.scoreservice.model.*;
import com.example.scoreservice.utils.SqlProvider;
import io.swagger.models.auth.In;
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

    @SelectProvider(type = SqlProvider.class, method = "selectItem")
    Map<String, Object> selectItem(@Param("PID") Integer PID, @Param("table") String table);


    @SelectProvider(type = SqlProvider.class, method = "selectScoreAndDate")
    List<Map<String, Object>> selectScoreAndDate(@Param("PID") int PID, @Param("table") String table);

    @DeleteProvider(type = SqlProvider.class, method = "deleteTable")
    int deleteTable(@Param("PID") int PID, @Param("SID") String SID, @Param("table") String table);

    @UpdateProvider(type = SqlProvider.class, method = "updateIdxAndScore")
    int updateIdxAndScore(@Param("PID") int PID, @Param("table") String table, @Param("idx") int idx, @Param("score") float score);

    @UpdateProvider(type = SqlProvider.class, method = "updateStatusOne")
    int updateStatusOne(@Param("PID") int PID, @Param("table") String table, @Param("status_one") int status_one);

    @UpdateProvider(type = SqlProvider.class, method = "updateStatusTwo")
    int updateStatusTwo(@Param("PID") int PID, @Param("SID") String SID, @Param("table") String table, @Param("status_two") int status_two);

//    @Select("<script>" +
//            "select score" +
//            "from ${table}" +
//            "where PID = #{PID}" +
//            "</script>")
//    float getScore(String table, Integer PID);

//    @Select("<script>" +
//            "select score, type" +
//            "from competition" +
//            "where PID = #{PID}" +
//            "</script>")
//    Map<String, Object> getScoreAndType(Integer PID);


    @Update("<script>" +
            "UPDATE gpa SET " +
            "com_bonus_total = (CASE" +
            "   WHEN com_bonus_total + #{score} > gpa * 0.2 THEN gpa * 0.2 " +
            "   ELSE com_bonus_total + #{score} " +
            "END), " +
            "com_score = gpa + com_bonus_total * 0.1, " +
            "<if test='table == \"morality\"'>" +
                " com_bonus1 = (CASE " +
                    " WHEN com_bonus1 + #{score} > 3 THEN 3 " +
                    " ELSE com_bonus1 + #{score} " +
                " END)" +
            "</if>" +
            "<if test='table == \"volunteer\"'>" +
                " com_bonus1 = (CASE " +
                    " WHEN com_bonus1 + #{score} > 3 THEN 3 " +
                    " ELSE com_bonus1 + #{score} " +
                " END), " +
                " com_bonus_vol = (CASE " +
                    " WHEN com_bonus_vol + #{score} > 0.75 THEN 0.75 " +
                    " ELSE com_bonus_vol + #{score} " +
                " END)" +
            "</if>" +
            "<if test='table == \"socialwork\"'>" +
                " com_bonus2 = (CASE " +
                    " WHEN com_bonus2 + #{score} > 3 THEN 3 " +
                    " ELSE com_bonus2 + #{score} " +
                " END)" +
            "</if>" +
            "<if test='table == \"art_competition\"'>" +
                " com_bonus3 = (CASE " +
                " WHEN com_bonus3 + #{score} > 4 THEN 4 " +
                " ELSE com_bonus3 + #{score} " +
                " END)" +
            "</if>" +
            "<if test='table == \"study_competition\" || table == \"paper\" || table == \"patent\" || table == \"copyright\" || table == \"publication\"'>" +
                " com_bonus4 = (CASE " +
                    " WHEN com_bonus4 + #{score} > 6 THEN 6 " +
                    " ELSE com_bonus4 + #{score} " +
                " END)" +
            "</if>" +
            " WHERE SID = #{SID} " +
            " AND sch_year = #{year} " +
            "</script>")
    int updateGpa(@Param("table") String table, @Param("SID") String SID, @Param("score") float score, @Param("year") int year);

    @Insert("insert into gpa(SID, sch_year, com_bonus_vol, com_bonus1, com_bonus2, com_bonus3, com_bonus4, com_bonus_total, com_score) values(#{SID}, #{year}, 0, 0, 0, 0, 0, 0, 0)")
    int insertNewGpa(@Param("SID") String SID, @Param("year") int year);

    @Select("<script>" +
            "select count(*) from gpa where " +
            " SID = #{SID} and " +
            " sch_year = #{year} " +
            "</script>")
    int selectGpa(@Param("SID") String SID, @Param("year") int year);

    @Select("select * from competition where type != 1")
    List<competition> getArtisticCompeition();

    @Select("select * from competition where type = 1")
    List<competition> getStudyCompeition();

    @Update("<script>" +
            "update ${table} " +
            "set status_one = #{status_one}, comment = #{comment}" +
            " where PID = #{PID}" +
            "</script>")
    int updateStatusOneByAdmin(Integer PID, String table, Integer status_one, String comment);

    @Update("<script>" +
            "update ${table} " +
            "set status_two = #{status_two}, comment = #{comment}" +
            " where PID = #{PID}" +
            "</script>")
    int updateStatusTwoByAdmin(Integer PID, String table, Integer status_two, String comment);

    /**
     * morality
     */
    @Insert("insert into morality(SID, idx, score, title, date, link_name, link, remarks, status_one, status_two, comment) " +
            "values(#{SID}, #{idx}, #{score}, #{title}, #{date}, #{link_name}, #{link}, #{remarks}, 0, -1, #{comment})")
    int submitMorality(morality moral);

    /**
     * volunteer
     */
    @Insert("insert into volunteer(SID, idx, score, title, organization, type, date_start, date_end, duration, link_name, link, remarks, status_one, status_two, comment) " +
            "values(#{SID}, #{idx}, #{score}, #{title}, #{organization}, #{type}, #{date_start}, #{date_end}, #{duration}, #{link_name}, #{link}, #{remarks}, 0, -1, #{comment})")
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



    @Update("update user_info set personal = personal + 1 where SID = #{SID}")
    int addPersonal(String SID);

    @Update("update user_info set personal = personal - 1 where SID = #{SID}")
    int subPersonal(String SID);

    @Update("update user_info set overall = overall + 1 where SID = #{SID}")
    int addOverall(String SID);

    @Update("update user_info set overall = overall - 1 where SID = #{SID}")
    int subOverall(String SID);

}
