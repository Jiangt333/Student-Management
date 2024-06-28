package com.example.authservice.dao;

import com.example.authservice.model.MailCode;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface MailDao {
    @Insert("insert into mailCode(toEmail, code, start_time) values(#{toEmail}, #{code}, #{start_time})")
    void saveCode(@Param("toEmail") String toEmail, @Param("code")String code, @Param("start_time")Date start_time);

    @Select("select * from mailCode where SID = #{SID} and toEmail = #{toEmail}")
    User validIdentity(@Param("SID") String SID, @Param("toEmail") String toEmail);

    @Select("select * from mailCode where toEmail = #{toEmail}")
    List<MailCode> selectCode(@Param("toEmail") String toEmail);

    @Delete("delete from mailCode where toEmail = #{toEmail}")
    void deleteCode(@Param("toEmail") String toEmail);
}
