package org.example.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.example.dataprovider.DataProviderExample;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Класс для тестирования различных сценариев входа в систему с использованием разных наборов данных.
 */

public class DataProviderTest extends BaseTest{

    /**
     * Тест для проверки авторизации с разными данными.
     */
    @Test(dataProvider = "testData", dataProviderClass = DataProviderExample.class,
            description = "Проверка входа с различными комбинациями данных")
    @Description("Тест проверяет вход в систему с различными комбинациями логина и пароля")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithDataProvider(String username, String password, boolean expectedResult) {
        LoginPage loginPage = new org.example.pages.LoginPage(getDriver());
        loginPage.login(username, password);

        if (expectedResult) {
            Assert.assertTrue(loginPage.isLoginSuccessful(), "Ожидался успешный вход для данных: " + username);
        } else {
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Ожидалась ошибка входа для данных: " + username);
        }
    }

}
