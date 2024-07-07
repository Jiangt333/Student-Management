package com.example.userservice.dao;

import com.example.userservice.model.BackendUser;
import com.example.userservice.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BackendUserDao {
    @Select("<script>" +
            "select * from user_info" +
            "<where>" +
            "<trim suffixOverrides=\"and\">" +   // 移除最后一个多出来的 and(<trim> 标签可以用于移除生成 SQL 语句中的不必要的字符)
            "<if test='SID != null'>SID = #{SID} and </if>" +
            "<if test='name != null'>name = #{name} and </if>" +
            "<if test='role != null'>role = #{role} and</if>" +
            "<if test='grade != grade'>grade = #{grade} and </if>" +
            "<if test='user_class != null'>user_class = #{user_class} and </if>" +
            "</trim>" +
            "</where>" +
            "</script>")
    List<BackendUser> findAllBackendUsers(String SID, String name, Integer role, String grade, String user_class);


    @Select("select count(*) from backend_user")
    int getBackendUserNum();

    @Select("select PID, SID, name, role, grade, user_class from backend_user where SID = #{SID}")
    BackendUser findBackendUser(@Param("SID") String SID);

    @Delete("<script>" +
            "delete from backend_user where SID IN" +
            "<foreach item='SID' collection='SIDList' open='(' separator=',' close=')'>" +
            "#{SID}" +
            "</foreach>" +
            "</script>")
    int deleteBackendUser(@Param("SIDList") List<String> SIDList);

    @Insert("insert into backend_user(SID, password, name, role, grade, user_class)" +
            " values(#{SID}, #{password}, #{name}, #{role}, #{grade}, #{user_class})")
    int addBackendUser(BackendUser BackendUser);

    @Update("<script>" +
            "update backend_user" +
            "<set>" +
            "<if test='password != null'>password = #{password},</if>" +
            "<if test='name != null'>name = #{name},</if>" +
            "<if test='role != null'>role = #{role},</if>" +
            "<if test='grade != null'>grade = #{grade},</if>" +
            "<if test='user_class != null'>user_class = #{user_class},</if>" +
            "</set>" +
            "where SID = #{SID}" +
            "</script>")
    int updateBackendUser(BackendUser BackendUser);
}
