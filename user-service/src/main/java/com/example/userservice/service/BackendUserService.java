package com.example.userservice.service;

import com.example.userservice.model.BackendUser;
import com.example.userservice.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackendUserService {
    List<BackendUser> getAllBackendUsers();
    BackendUser findBackendUser(int SID);
    int deleteBackendUser(int SID);
}
