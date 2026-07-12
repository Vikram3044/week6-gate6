package com.shopkart.config;

import java.io.IOException;
import java.util.Properties;

public final class AppConfig {
    private static final Properties PROPERTIES = load();
    private AppConfig() { }
    private static Properties load() {
        String profile = Env.value("shopkart.profile", "local");
        Properties properties = new Properties();
        try (var input = AppConfig.class.getResourceAsStream("/config/application-" + profile + ".properties")) {
            if (input == null) throw new IllegalStateException("Unknown profile: " + profile);
            properties.load(input);
            return properties;
        } catch (IOException exception) { throw new IllegalStateException("Cannot load configuration", exception); }
    }
    public static String baseUrl() {
        return Env.value("shopkart.base-url", PROPERTIES.getProperty("shopkart.base-url"));
    }
    public static String apiUrl() {
        return Env.value("shopkart.api-url", PROPERTIES.getProperty("shopkart.api-url"));
    }
    public static boolean headless() {
        return Boolean.parseBoolean(Env.value("shopkart.browser", PROPERTIES.getProperty("shopkart.browser")));
    }
}
