package com.example.userservice.dao;

import com.example.userservice.model.BackendUser;
import com.example.userservice.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BackendUserDao {
    /**
     * 获取所有用户全部信息
     * @return
     */
    @Select("select * from backend_user")
    List<BackendUser> findAllBackendUsers();

    @Select("select * from backend_user where SID = #{SID}")
    BackendUser findBackendUser(@Param("SID") int SID);

    @Delete("delete from backend_user where SID = #{SID}")
    int deleteBackendUser(@Param("SID") int SID);
}
