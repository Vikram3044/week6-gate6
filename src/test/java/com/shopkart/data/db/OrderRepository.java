package com.shopkart.data.db;

import java.sql.DriverManager;
import com.shopkart.data.secret.Secrets;

/** Read-only DB assertions. Use the ShopKart app DB credentials provided by the environment. */
public final class OrderRepository {
    private OrderRepository() { }
    public static long placedCount(long orderId, long customerId, int totalPaise) {
        String url = databaseUrl(), user = value("SHOPKART_DB_USER", "DB_USER"), password = value("SHOPKART_DB_PASSWORD", "DB_PASSWORD");
        String sql = "SELECT COUNT(*) FROM orders WHERE id = ? AND customer_id = ? AND status = 'PLACED' AND total_paise = ?";
        try (var connection = DriverManager.getConnection(url, user, password); var statement = connection.prepareStatement(sql)) {
            statement.setLong(1, orderId); statement.setLong(2, customerId); statement.setInt(3, totalPaise);
            try (var result = statement.executeQuery()) { result.next(); return result.getLong(1); }
        } catch (Exception exception) { throw new IllegalStateException("Database assertion failed", exception); }
    }
    private static String databaseUrl() {
        String configured = System.getenv("SHOPKART_DB_URL");
        if (configured != null && !configured.isBlank()) return configured;
        return "jdbc:mysql://" + Secrets.get("DB_HOST") + ":" + Secrets.get("DB_PORT") + "/" + Secrets.get("DB_NAME");
    }
    private static String value(String preferredEnvironmentKey, String appKey) {
        String configured = System.getenv(preferredEnvironmentKey);
        return configured == null || configured.isBlank() ? Secrets.get(appKey) : configured;
    }
}
