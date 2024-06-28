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
        return backendUserDao.findAllBackendUsers();
    }

    @Override
    public BackendUser findBackendUser(int SID){
        return backendUserDao.findBackendUser(SID);
    }

    @Override
    public int deleteBackendUser(int SID){
        if(backendUserDao.deleteBackendUser(SID) == 1){
            return 1;
        }
        return 0;
    }
}
