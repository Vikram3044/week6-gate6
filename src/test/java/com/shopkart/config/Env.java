package com.shopkart.config;

public final class Env {
    private Env() { }
    public static String value(String key, String fallback) {
        String fromProperty = System.getProperty(key);
        if (fromProperty != null && !fromProperty.isBlank()) return fromProperty;
        String fromEnvironment = System.getenv(key.toUpperCase().replace('.', '_'));
        return fromEnvironment == null || fromEnvironment.isBlank() ? fallback : fromEnvironment;
    }
}
