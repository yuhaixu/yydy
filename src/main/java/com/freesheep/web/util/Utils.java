package com.freesheep.web.util;

import com.alibaba.fastjson.JSONObject;

import java.util.UUID;

public class Utils {

    public static String getKey(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getSomeValue(Object value){
        String re = "";
        if(value != null){
            re = value.toString();
        }
        return re;
    }

    public static double parseDouble(String numStr){
        double num = 0;
        try {
            num = Double.parseDouble(numStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
        return num;
    }

    public static float parseFloat(String numStr){
        float num = 0;
        try {
            num = Float.parseFloat(numStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
        return num;
    }

    public static int parseInt(String numStr){
        int num = 0;
        try {
            num = Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
        return num;
    }

    public static long parseLong(String numStr){
        long num = 0;
        try {
            num = Long.parseLong(numStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
        return num;
    }

    public static JSONObject getSuccessJson(){
        JSONObject json = new JSONObject();
        json.put("status","1");
        return json;
    }

}
