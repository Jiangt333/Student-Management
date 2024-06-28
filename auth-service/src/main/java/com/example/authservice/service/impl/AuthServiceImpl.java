package com.example.authservice.service.impl;

import com.example.authservice.dao.AuthDao;
import com.example.authservice.model.SimpleUser;
import com.example.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthDao authDao;

    @Override
    public boolean userLogin(String SID, String password) {
        SimpleUser myUser = authDao.findByUsernameAndPassword(SID, password);
        if(myUser != null)
            return true;
        return false;
    }

    @Override
    public boolean backendUserLogin(String SID, String password) {
        SimpleUser myUser = authDao.findByBackendUsernameAndPassword(SID, password);
        if(myUser != null)
            return true;
        return false;
    }

    @Override
    public boolean updateUserPassword(String SID, String password){
        if(authDao.updateUserPassword(SID, password) == 1){
            return true;
        }
        return false;
    }

}
