package com.example.authservice.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface MailDao {
    @Insert("insert into mailCode(toEmail, code, start_time) values(#{toEmail}, #{code}, #{start_time}")
    void saveCode(@Param("toEmail") String toEmail, @Param("code")String code, @Param("start_time")Date start_time);
}
