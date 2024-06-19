package com.example.authservice.dao;

import com.example.authservice.model.SimpleUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthDao {
    /**
     * 登录判断
     * @return
     */
    @Select("select * from user_info where SID = #{SID} and password = #{password}")
    SimpleUser findByUsernameAndPassword(String SID, String password);
}
