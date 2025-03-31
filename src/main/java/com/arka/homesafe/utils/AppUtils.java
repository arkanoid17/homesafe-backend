package com.arka.homesafe.utils;

import java.util.Map;

public class AppUtils {
    public String getTokenFromHeader(Map<String,String> headers){
        for(String key:headers.keySet()){
            if(key.equalsIgnoreCase(StringUtils.AUTHORIZATION)){
                return headers.get(key).substring(7);
            }
        }

        return null;
    }
}
