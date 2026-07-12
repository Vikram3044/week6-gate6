package com.shopkart.data.db;

import org.testcontainers.containers.MySQLContainer;

/** Per-class disposable MySQL instance for migration and isolation checks. */
public final class MySqlSupport {
    private static final MySQLContainer<?> MYSQL = new MySQLContainer<>("mysql:8.4").withDatabaseName("shopkart").withUsername("shopkart_user").withPassword("123456789");
    private MySqlSupport() { }
    public static synchronized MySQLContainer<?> start() {
        if (!MYSQL.isRunning())
        {
            MYSQL.start(); FlywaySupport.migrate(MYSQL.getJdbcUrl(), MYSQL.getUsername(), MYSQL.getPassword());
        }
        return MYSQL; }
}
