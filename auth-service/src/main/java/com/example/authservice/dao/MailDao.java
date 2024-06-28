package com.example.authservice.dao;

import com.example.authservice.model.MailCode;
import com.example.userservice.model.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface MailDao {
    @Insert("insert into mailCode(toEmail, code, start_time) values(#{toEmail}, #{code}, #{start_time})")
    void saveCode(@Param("toEmail") String toEmail, @Param("code")String code, @Param("start_time")Date start_time);

    @Select("select * from user_info where SID = #{SID} and email = #{email}")
    User validIdentity(@Param("SID") String SID, @Param("email") String email);

    @Select("select * from mailCode where toEmail = #{toEmail}")
    List<MailCode> selectCode(@Param("toEmail") String toEmail);

    @Delete("delete from mailCode where toEmail = #{toEmail}")
    void deleteCode(@Param("toEmail") String toEmail);
}
