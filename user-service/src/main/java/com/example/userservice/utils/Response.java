package com.example.userservice.utils;

import org.springframework.stereotype.Component;

@Component("userServiceResponse")
public class Response<T> {
    public int code = 200;
    public T data;
}
