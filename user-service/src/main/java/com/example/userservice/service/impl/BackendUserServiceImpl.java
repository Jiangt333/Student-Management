package com.example.userservice.service.impl;

import com.example.userservice.dao.BackendUserDao;
import com.example.userservice.model.BackendUser;
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
    public int getBackendUserNum(){
        return backendUserDao.getBackendUserNum();
    }

    @Override
    public BackendUser findBackendUser(String SID){
        return backendUserDao.findBackendUser(SID);
    }

    @Override
    public int deleteBackendUser(List<String> SIDList){
        if(backendUserDao.deleteBackendUser(SIDList) == 0){
            return 0;
        }
        return 1;
    }

    @Override
    public int addBackendUser(BackendUser backendUser){
        if(backendUserDao.findBackendUser(backendUser.getSID()) != null)
            return -1;

        if(backendUserDao.addBackendUser(backendUser) == 1)
            return 1;
        return 0;
    }

    @Override
    public int updateBackendUser(BackendUser backendUser){
        if(backendUserDao.updateBackendUser(backendUser) == 1)
            return 1;
        return 0;
    }
}
