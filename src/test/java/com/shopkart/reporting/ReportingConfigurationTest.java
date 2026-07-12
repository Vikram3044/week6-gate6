package com.shopkart.reporting;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Framework Hardening")
@Feature("Reporting")
@Owner("Vikram")
class ReportingConfigurationTest {
    private static final Path CATEGORIES = Path.of("src/test/resources/categories.json");

    @Test
    @Story("Categories")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify categories.json exists.")
    void categoriesFileShouldExist() {
        assertTrue(Files.exists(CATEGORIES), "categories.json should exist");
    }

    @Test
    @Story("Categories")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify required categories exist.")
    void requiredCategoriesShouldExist() throws Exception {
        String categories = Files.readString(CATEGORIES);
        assertAll(
                () -> assertTrue(categories.contains("Assertion Failures")),
                () -> assertTrue(categories.contains("UI Automation Issues")),
                () -> assertTrue(categories.contains("Database Issues")),
                () -> assertTrue(categories.contains("Configuration Issues")),
                () -> assertTrue(categories.contains("Infrastructure Issues")),
                () -> assertTrue(categories.contains("Other Failures"))
        );
    }

    @Test
    @Story("Categories")
    @Severity(SeverityLevel.NORMAL)
    @Description("Specific categories must be listed before the generic fallback.")
    void specificCategoriesShouldComeBeforeOtherFailures() throws Exception {
        String categories = Files.readString(CATEGORIES);
        int fallback = categories.indexOf("Other Failures");
        assertTrue(categories.indexOf("Assertion Failures") < fallback);
        assertTrue(categories.indexOf("UI Automation Issues") < fallback);
        assertTrue(categories.indexOf("Database Issues") < fallback);
        assertTrue(categories.indexOf("Configuration Issues") < fallback);
        assertTrue(categories.indexOf("Infrastructure Issues") < fallback);
    }

    @Test
    @Story("Categories")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify important failure patterns exist.")
    void regexPatternsShouldExist() throws Exception {
        String categories = Files.readString(CATEGORIES);
        assertAll(
                () -> assertTrue(categories.contains("AssertionFailedError")),
                () -> assertTrue(categories.contains("NoSuchElementException")),
                () -> assertTrue(categories.contains("SQLException")),
                () -> assertTrue(categories.contains("IllegalArgumentException"))
        );
    }
}
