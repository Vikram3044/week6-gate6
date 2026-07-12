package com.shopkart.data.secret;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/** Resolves secret values without ever logging them. */
public final class Secrets {
    private Secrets() { }
    public static String get(String key) {
        String environmentKey = "SHOPKART_" + key.toUpperCase().replace(".PASSWORD", "_PW").replace('.', '_');
        String legacyEnvironmentKey = "SHOPKART_" + key.toUpperCase().replace('.', '_');
        String value = System.getenv(environmentKey);
        if (value == null || value.isBlank()) value = System.getenv(legacyEnvironmentKey);
        if (value == null || value.isBlank()) value = System.getProperty(environmentKey);
        if (value == null || value.isBlank()) value = local().getProperty(key);
        if (value == null || value.isBlank()) value = local().getProperty(environmentKey);
        if (value == null || value.isBlank()) value = local().getProperty(legacyEnvironmentKey);
        if (value == null || value.isBlank()) throw new IllegalStateException("Missing required secret " + environmentKey);
        return value;
    }
    private static Properties local() {
        Properties secrets = new Properties();
        String configuredPath = System.getenv("SHOPKART_SECRETS_FILE");
        Path path;
        if (configuredPath != null && !configuredPath.isBlank()) path = Path.of(configuredPath);
        else {
            Path projectLocal = Path.of("secrets.local.properties");
            // Convenience for this workspace's supplied app; its .env remains outside this repository.
            path = Files.exists(projectLocal) ? projectLocal : Path.of("..", "sdet-retail-app", ".env");
        }
        if (Files.exists(path)) try (var input = Files.newInputStream(path)) { secrets.load(input); }
        catch (IOException exception) { throw new IllegalStateException("Cannot read local secret file", exception); }
        return secrets;
    }
}
