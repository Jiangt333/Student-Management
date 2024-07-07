package com.example.userservice.service;

import com.example.userservice.model.BackendUser;

import java.util.List;

public interface BackendUserService {
    List<BackendUser> getAllBackendUsers(String SID, String name, Integer role, String grade, String user_class);
    int getBackendUserNum();
    BackendUser findBackendUser(String SID);
    int deleteBackendUser(List<String> SIDList);
    int addBackendUser(BackendUser backendUser);
    int updateBackendUser(BackendUser backendUser);
}
