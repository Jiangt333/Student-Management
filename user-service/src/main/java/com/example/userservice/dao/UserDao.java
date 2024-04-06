package com.example.userservice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.userservice.model.User;

import java.util.List;

@Mapper
public interface UserDao {
    /**
     * 登录判断
     * @return
     */
    @Select("select * from user where SID = #{SID} and SPassword = #{SPassword}")
    User findByUsernameAndPassword(String SID, String SPassword);

    /**
     * 获取所有用户全部信息
     * @return
     */
    @Select("select * from user")
    List<User> findAllUsers();

    /**
     * 获取某个用户全部信息
     * @return
     */
    @Select("select * from user where sid = #{SID}")
    User findUserById(String SID);
}
