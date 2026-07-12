package com.shopkart.data.db;

import org.flywaydb.core.Flyway;

public final class FlywaySupport {
    private FlywaySupport() { }
    public static void migrate(String url, String user, String password) {
        Flyway.configure().dataSource(url, user, password).locations("classpath:db/migration").load().migrate();
    }
}
