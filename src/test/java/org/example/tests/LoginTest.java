package org.example.tests;

import io.qameta.allure.*;
import org.example.dataprovider.DataProviderExample;
import org.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.config.MyConfig.*;

/**
 * Тестовый класс для проверки функциональности входа в систему.
 */
public class LoginTest extends BaseTest {

    @Test(description = "Проверка успешного входа с валидными учетными данными")
    @Description("Тест проверяет возможность входа в систему с корректными логином и паролем")
    @Severity(SeverityLevel.CRITICAL)
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        Assert.assertTrue(loginPage.isLoginSuccessful(), "Проверка, что вход выполнен успешно и отображается страница дашбордов");
    }

    @Test(description = "Проверка входа с неверными учетными данными")
    @Description("Тест проверяет отображение сообщения об ошибке при вводе некорректных данных")
    @Severity(SeverityLevel.NORMAL)
    public void testFailedLoginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(INVALID_USERNAME, INVALID_PASSWORD);

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Проверка, что сообщение об ошибке отображается");
        Assert.assertFalse(loginPage.getErrorMessageText().isEmpty(), "Проверка, что текст сообщения об ошибке не пустой");
    }
}
