package org.example.tests;

import io.qameta.allure.*;
import org.example.pages.*;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.config.MyConfig.*;

/**
 * Тестовый класс для проверки создания виджетов.
 */
public class WidgetCreationTest extends BaseTest {

    @Test(description = "Проверка создания виджета 'Task Progress'")
    @Description("Тест проверяет возможность создания нового виджета типа 'Task Progress' на дашборде")
    @Severity(SeverityLevel.BLOCKER)
    public void testCreateTaskProgressWidget() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.createNewDashboard("My New Dashboard", "Test Description");

        WidgetPage widgetPage = new WidgetPage(getDriver());
        widgetPage.clickButtonFilters()
                .clickButtonDashboard()
                .clickDashboardName();

    }
}