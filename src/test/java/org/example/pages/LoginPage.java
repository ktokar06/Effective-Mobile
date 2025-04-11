package org.example.pages;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Класс, представляющий страницу авторизации в Report Portal.
 * Обеспечивает взаимодействие с элементами формы входа и методы для выполнения операций авторизации.
 */
public class LoginPage {

    /**
     * Драйвер для взаимодействия с браузером
     */
    private final WebDriver driver;

    /**
     * Ожидание для работы с элементами страницы
     */
    private final WebDriverWait wait;

    /**
     * Поле ввода имени пользователя
     */
    @FindBy(name = "login")
    private WebElement usernameField;

    /**
     * Поле ввода пароля
     */
    @FindBy(name = "password")
    private WebElement passwordField;

    /**
     * Кнопка входа в систему
     */
    @FindBy(css = "button.bigButton__big-button--BmG4Q")
    private WebElement loginButton;

    /**
     * Сообщение об ошибке авторизации
     */
    @FindBy(css = "[class*='field-error-hint']")
    private WebElement errorMessage;

    /**
     * Заголовок "All Dashboards", отображаемый после успешного входа
     */
    @FindBy(css = "ul.pageBreadcrumbs__page-breadcrumbs--P8I6V li span[title='All Dashboards']")
    private WebElement allDashboardsTitle;

    /**
     * Конструктор класса LoginPage.
     * Инициализирует экземпляр страницы авторизации.
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Вводит указанное имя пользователя в соответствующее поле.
     *
     * @param username имя пользователя для ввода
     */
    @Step("Ввод имени пользователя: {username}")
    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    /**
     * Вводит указанный пароль в соответствующее поле.
     *
     * @param password пароль для ввода
     */
    @Step("Ввод пароля")
    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    /**
     * Нажимает кнопку входа в систему.
     * Ожидает, пока кнопка станет кликабельной перед нажатием.
     */
    @Step("Нажатие кнопки 'Login'")
    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    /**
     * Выполняет полный процесс авторизации.
     * Вводит имя пользователя и пароль, затем нажимает кнопку входа.
     *
     * @param username имя пользователя для входа
     * @param password пароль для входа
     */
    @Step("Авторизация пользователя {username}")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    /**
     * Проверяет, отображается ли сообщение об ошибке авторизации.
     *
     * @return true если сообщение об ошибке видимо, false в противном случае
     */
    @Step("Проверка отображения сообщения об ошибке")
    public boolean isErrorMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Получает текст сообщения об ошибке авторизации.
     *
     * @return текст сообщения об ошибке
     * @throws org.openqa.selenium.TimeoutException если сообщение не становится видимым в течение времени ожидания
     */
    @Step("Получение текста сообщения об ошибке")
    public String getErrorMessageText() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }

    /**
     * Проверяет, был ли вход в систему выполнен успешно.
     * Определяет успешность входа по наличию элемента "All Dashboards".
     *
     * @return true если вход выполнен успешно, false в противном случае
     */
    @Step("Проверка успешного входа в систему")
    public boolean isLoginSuccessful() {
        return isAllDashboardsDisplayed();
    }

    /**
     * Проверяет, отображается ли элемент "All Dashboards",
     * который появляется после успешного входа в систему.
     *
     * @return true если элемент видим, false в противном случае
     */
    @Step("Проверка отображения элемента 'All Dashboards' после входа")
    public boolean isAllDashboardsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(allDashboardsTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}