package com.example.scoreservice.utils;

import org.springframework.stereotype.Component;

@Component("scoreServiceResponse")
public class Response<T> {
    public int code = 200;
    public T data;
}
