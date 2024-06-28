package com.example.authservice.dao;

import com.example.authservice.model.SimpleUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AuthDao {
    /**
     * 前台用户登录判断
     * @return
     */
    @Select("select * from user_info where SID = #{SID} and password = #{password}")
    SimpleUser findByUsernameAndPassword(String SID, String password);

    /**
     * 后台用户登录判断
     * @return
     */
    @Select("select * from backend_user where SID = #{SID} and password = #{password}")
    SimpleUser findByBackendUsernameAndPassword(String SID, String password);

    @Update("update user_info set password = #{password} where SID = #{SID}")
    int updateUserPassword(String SID, String password);

    @Update("update backend_user set password = #{password} where SID = #{SID}")
    int updateBackendUserPassword(String SID, String password);
}
