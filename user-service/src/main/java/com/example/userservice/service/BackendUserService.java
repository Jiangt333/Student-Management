package com.example.userservice.service;

import com.example.userservice.model.BackendUser;
import com.example.userservice.model.User;

import java.util.List;

public interface BackendUserService {
    List<BackendUser> getAllBackendUsers();
}
