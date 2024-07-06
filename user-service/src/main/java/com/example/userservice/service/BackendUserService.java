package com.example.userservice.service;

import com.example.userservice.model.BackendUser;

import java.util.List;

public interface BackendUserService {
    List<BackendUser> getAllBackendUsers();
    int getBackendUserNum();
    BackendUser findBackendUser(String SID);
    int deleteBackendUser(List<String> SIDList);
    int addBackendUser(BackendUser backendUser);
    int updateBackendUser(BackendUser backendUser);
}
