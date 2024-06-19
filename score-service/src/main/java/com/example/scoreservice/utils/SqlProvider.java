package com.example.scoreservice.utils;

import java.util.Map;

public class SqlProvider {
    public String selectTable(Map<String, Object> params) {
        String table = (String) params.get("table");
        String SID = (String) params.get("SID");
        return "SELECT * FROM " + table + " WHERE SID = '" + SID + "'";
    }

    public String deleteTable(Map<String, Object> params) {
        String table = (String) params.get("table");
        String SID = (String) params.get("SID");
        String PID = (String) params.get("PID");
        return "delete FROM " + table + " WHERE SID = '" + SID + "' and PID = '" + PID + "'";
    }
}
