package com.example.userservice.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.userservice.model.user;

import java.util.List;

@Mapper
public interface userDao {
    /**
     * 获取所有用户全部信息
     * @return
     */
    @Select("select * from user")
    List<user> findAllUsers();

    /**
     * 获取某个用户全部信息
     * @return
     */
    @Select("select * from user where sid = #{id}")
    user findUserById(int id);
}
