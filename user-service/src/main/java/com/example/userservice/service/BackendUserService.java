package com.example.userservice.service;

import com.example.userservice.model.BackendUser;
import com.example.userservice.model.SimpleBackendUser;
import com.example.userservice.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackendUserService {
    List<BackendUser> getAllBackendUsers();
    int getBackendUserNum();
    BackendUser findBackendUser(String SID);
    int deleteBackendUser(List<String> SIDList);
    int addBackendUser(BackendUser backendUser);
    int updateBackendUser(BackendUser backendUser);
}
