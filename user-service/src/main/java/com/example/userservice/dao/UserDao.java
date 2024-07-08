package com.example.userservice.dao;

import com.example.userservice.model.SimpleUser;
import org.apache.ibatis.annotations.*;

import com.example.userservice.model.User;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("<script>" +
            "select * from user_info" +
            "<where>" +
            "<trim suffixOverrides=\"and\">" +   // 移除最后一个多出来的 and(<trim> 标签可以用于移除生成 SQL 语句中的不必要的字符)
            "<if test='SID != null'>SID LIKE CONCAT('%', #{SID}, '%') and </if>" +
            "<if test='name != null'>name LIKE CONCAT('%', #{name}, '%') and </if>" +
            "<if test='grade != null'>grade = #{grade} and </if>" +
            "<if test='user_class != null'>user_class = #{user_class} and </if>" +
            "<if test='type != null'>type = #{type} and </if>" +
            "<if test='level != null'>level = #{level} and </if>" +
            "<if test='outlook != null'>outlook = #{outlook} and </if>" +
            "<if test='personal != null'>" +
            "   <choose>" +
            "       <when test='personal != 0'>personal = 0</when>" +
            "       <otherwise>personal = 0</otherwise>" +
            "   </choose> and " +
            "</if>" +
            "<if test='overall != null'>" +
            "   <choose>" +
            "       <when test='overall != 0'> overall = 0</when>" +
            "       <otherwise> overall = 0</otherwise>" +
            "   </choose>" +
            "</if>" +
            "</trim>" +
            "</where>" +
            "</script>")
    List<User> findAllUsers(String SID, String name, String grade, String user_class,
                            Integer type, Integer level, Integer outlook, Integer personal, Integer overall);

    @Select("select count(*) from user_info")
    int getUserNum();

    @Select("select * from user_info where SID = #{SID}")
    User findUserById(@Param("SID") String SID);

    @Update("update user_info set outlook = #{outlook}, tel = #{tel}, wechat = #{wechat}, email = #{email}, address = #{address}, emergency_name = #{emergency_name}, emergency_tel = #{emergency_tel} where SID = #{SID}")
    int updateUserById(User user);

    @Delete("<script>" +
            "delete from user_info where SID IN" +
            "<foreach item='SID' collection='SIDList' open='(' separator=',' close=')'>" +
            "#{SID}" +
            "</foreach>" +
            "</script>")
    int deleteUser(@Param("SIDList") List<String> SIDList);

    @Insert("<script>" +
            "insert into user_info (SID, name, type, gender, nation, identity, birthday, grade, user_class, level, outlook, dorm, " +
            "user_native, tel, address, emergency_name, emergency_tel, wechat, email) values" +
            "<foreach item='user' collection='userList' separator=','>" +
            "(#{user.SID}, #{user.name}, #{user.type}, #{user.gender}, #{user.nation}, #{user.identity}, #{user.birthday}, #{user.grade}, " +
            "#{user.user_class}, #{user.level}, #{user.outlook}, #{user.dorm}," +
            "#{user.user_native}, #{user.tel}, #{user.address}, #{user.emergency_name}, #{user.emergency_tel}, #{user.wechat}, #{user.email})" +
            "</foreach>" +
            "</script>")
    int addUser(@Param("userList") List<SimpleUser> userList);

}
