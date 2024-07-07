package com.example.scoreservice.utils;

public class myException {
    public static class SqlOperationException extends Exception {
        public SqlOperationException(String message) {
            super(message);
        }
    }

    public static class NoMatchingRecordException extends Exception {
        public NoMatchingRecordException(String message) {
            super(message);
        }
    }
}
