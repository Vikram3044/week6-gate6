package com.shopkart.data.db;

import org.junit.jupiter.api.Test;

import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** CI proof that a fresh MySQL container receives the public Flyway baseline. */
class MySqlContainerSmokeTest {
    @Test
    void migratesPublicCatalogueIntoAnIsolatedMySqlContainer() throws Exception {
        var mysql = MySqlSupport.start();
        try (var connection = DriverManager.getConnection(mysql.getJdbcUrl(), mysql.getUsername(), mysql.getPassword());
             var statement = connection.createStatement();
             var result = statement.executeQuery("SELECT COUNT(*) FROM products")) {
            result.next();
            assertEquals(6, result.getInt(1));
        }
    }
}
