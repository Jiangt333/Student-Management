package com.example.userservice.service;

import com.example.userservice.model.SimpleUser;
import com.example.userservice.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {

    List<User> getAllUsers(String SID, String name, String grade, String user_class,
                           Integer type, Integer level, Integer outlook, Integer personal, Integer overall);
    int getUserNum();
    User getUserById(String SID);
    int updateUserById(User user);
    int deleteUser(List<String> SIDList);
    int addUser(List<SimpleUser> userList);
}
