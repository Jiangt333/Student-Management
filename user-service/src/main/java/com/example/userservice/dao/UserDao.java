package com.example.userservice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.userservice.model.User;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserDao {
    /**
     * 获取所有用户全部信息
     * @return
     */
    @Select("select * from user_info")
    List<User> findAllUsers();

    /**
     * 获取某个用户全部信息
     * @return
     */
    @Select("select * from user_info where SID = #{SID}")
    User findUserById(@Param("SID") String SID);

    @Update("update user_info set outlook = #{outlook}, tel = #{tel}, wechat = #{wechat}, email = #{email}, address = #{address}, emergency_name = #{emergency_name}, emergency_tel = #{emergency_tel} where SID = #{SID}")
    int updateUserById(User user);
}
