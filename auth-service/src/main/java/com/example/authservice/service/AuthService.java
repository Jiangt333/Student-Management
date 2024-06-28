package com.example.authservice.service;

public interface AuthService {
    boolean userLogin(String SID, String password);
    boolean backendUserLogin(String SID, String password);
    boolean updateUserPassword(String SID, String password);
}
