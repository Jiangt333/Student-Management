package com.example.userservice.dao;

import com.example.userservice.model.BackendUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BackendUserDao {
    /**
     * 获取所有用户全部信息
     * @return
     */
    @Select("select PID, SID, name, role, grade, user_class from backend_user")
    List<BackendUser> findAllBackendUsers();

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
