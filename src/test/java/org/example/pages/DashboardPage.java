package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "button.ghostButton__ghost-button--r7c9T .ghostButton__text--SjHtK")
    private WebElement addNewDashboardButton;

    @FindBy(css = "input.input__input--iYEmM[placeholder='Enter dashboard name']")
    private WebElement dashboardNameInput;

    @FindBy(css = "textarea.inputTextArea__input-text-area--N0goa")
    private WebElement dashboardDescriptionTextarea;

    @FindBy(css = "button.bigButton__big-button--BmG4Q.bigButton__color-booger--EpRlL")
    private WebElement buttonAddNewDashboard;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Step("Click 'Add New Dashboard' button")
    public DashboardPage clickAddNewDashboard() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addNewDashboardButton)).click();
        } catch (StaleElementReferenceException e) {
            // Re-locate the element and try again
            addNewDashboardButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("button.ghostButton__ghost-button--r7c9T .ghostButton__text--SjHtK")));
            addNewDashboardButton.click();
        }
        return this;
    }

    @Step("Set dashboard name: {name}")
    public DashboardPage setDashboardName(String name) {
        wait.until(ExpectedConditions.visibilityOf(dashboardNameInput)).sendKeys(name);
        return this;
    }

    @Step("Set dashboard description: {description}")
    public DashboardPage setDashboardDescription(String description) {
        wait.until(ExpectedConditions.visibilityOf(dashboardDescriptionTextarea)).sendKeys(description);
        return this;
    }

    @Step("Click 'Add' button to confirm dashboard creation")
    public DashboardPage clickAddDashboardConfirm() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonAddNewDashboard)).click();
        return this;
    }

    @Step("Create a new dashboard with name: {name} and description: {description}")
    public DashboardPage createNewDashboard(String name, String description) {
        return clickAddNewDashboard()
                .setDashboardName(name)
                .setDashboardDescription(description)
                .clickAddDashboardConfirm();
    }
}