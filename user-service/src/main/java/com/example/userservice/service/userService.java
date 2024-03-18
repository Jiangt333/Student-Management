package com.example.userservice.service;

import com.example.userservice.model.user;

import java.util.List;

public interface userService {

    List<user> getAllUsers();

    user getUserById(int id);
}
