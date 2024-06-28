package com.example.userservice.service.impl;

import com.example.userservice.dao.BackendUserDao;
import com.example.userservice.dao.UserDao;
import com.example.userservice.model.BackendUser;
import com.example.userservice.model.User;
import com.example.userservice.service.BackendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackendUserServiceImpl implements BackendUserService {
    @Autowired
    private BackendUserDao backendUserDao;

    @Override
    public List<BackendUser> getAllBackendUsers() {
        List<BackendUser> backendUserList = backendUserDao.findAllBackendUsers();
        return backendUserList;
    }
}
