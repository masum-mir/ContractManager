package com.scm.config;

public class EnvConfig {

    public static String getString(String key, String defaultValue) {
        String value = System.getenv(key);
        if(value!= null) {
            return value;
        } else {
            return defaultValue;
        }
    }

    public static long getLong(String key, long defaultValue) {
        try {
            String value = System.getenv(key);
            if(value!= null) {
                return Long.parseLong(value);
            }
        } catch (Exception ignored) {}
        return defaultValue;
    }
}
