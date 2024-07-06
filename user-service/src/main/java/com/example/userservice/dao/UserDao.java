package com.example.userservice.dao;

import org.apache.ibatis.annotations.*;

import com.example.userservice.model.User;

import java.util.List;

@Mapper
public interface UserDao {
    /**
     * 获取所有用户全部信息
     * @return
     */
    @Select("select * from user_info")
    List<User> findAllUsers();

    @Select("select count(*) from user_info")
    int getUserNum();

    /**
     * 获取某个用户全部信息
     * @return
     */
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

}
