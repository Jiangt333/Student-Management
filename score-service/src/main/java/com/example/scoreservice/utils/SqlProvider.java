package com.example.scoreservice.utils;

import java.util.Map;

public class SqlProvider {
    public String selectTable(Map<String, Object> params) {
        String table = (String) params.get("table");
        String SID = (String) params.get("SID");
        return "SELECT * FROM " + table + " WHERE SID = '" + SID + "'";
    }

    public String selectScoreAndDate(Map<String, Object> params) {
        String table = (String) params.get("table");
        int PID = (int) params.get("PID");
        return "SELECT score, date FROM " + table + " WHERE PID = '" + PID + "'";
    }

    public String deleteTable(Map<String, Object> params) {
        String table = (String) params.get("table");
        String SID = (String) params.get("SID");
        int PID = (int) params.get("PID");
        return "delete FROM " + table + " WHERE SID = '" + SID + "' and PID = '" + PID + "'";
    }

    public String updateIdxAndScore(Map<String, Object> params) {
        String table = (String) params.get("table");
        int PID = (int) params.get("PID");
        int idx = (int) params.get("idx");
        float score = (float) params.get("score");
        return "update " + table + " set idx = '" + idx + "' and score = '" + score + "' WHERE PID = '" + PID + "'";
    }

    public String updateStatusOne(Map<String, Object> params) {
        String table = (String) params.get("table");
        int PID = (int) params.get("PID");
        int status_one = (int) params.get("status_one");
        return "update " + table + " set status_one = '" + status_one + "' WHERE PID = '" + PID + "'";
    }

    public String updateStatusTwo(Map<String, Object> params) {
        String table = (String) params.get("table");
        int PID = (int) params.get("PID");
        int status_two = (int) params.get("status_two");
        return "update " + table + " set status_two = '" + status_two + "' WHERE PID = '" + PID + "'";
    }
}
