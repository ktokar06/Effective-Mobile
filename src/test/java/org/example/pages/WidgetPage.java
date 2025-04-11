package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WidgetPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "a.dashboardTable__name--t2a89")
    private WebElement dashboardName;

    @FindBy(css = ".dashboardItemPage__buttons-block--QoL50 button.ghostButton__ghost-button--r7c9T")
    private WebElement buttonAddNewWidget;

    @FindBy(css = "label.inputRadio__input-radio--EMMTx input[value='passingRatePerLaunch']")
    private WebElement buttonPassingRatePerLaunch;

    @FindBy(css = "span.ghostButton__text--SjHtK")
    private WebElement buttonNext;

    @FindBy(id = "downshift-3-input")
    private WebElement launchNameField;

    @FindBy(className = "input__input--iYEmM")
    private WebElement widgetNameField;

    @FindBy(className = "inputTextArea__input-text-area--N0goa")
    private WebElement descriptionField;

    @FindBy(css = "button.bigButton__big-button--BmG4Q")
    private WebElement addWidgetButton;

    @FindBy(css = ".widgetHeader__widget-name-block--AOAHS")
    private WebElement createdWidgetName;

    @FindBy(css = "a[href='#default_personal/filters']")
    private WebElement buttonFilters;

    @FindBy(css = "a[href='#default_personal/dashboard']")
    private WebElement buttonDashboard;

    public WidgetPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Step("Click 'Add New Widget' button")
    public void clickDashboardName() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonAddNewWidget)).click();
    }

    @Step("Click 'Add New Widget' button")
    public void clickAddNewWidget() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonAddNewWidget)).click();
    }

    @Step("Select 'Passing Rate Per Launch' widget type")
    public void selectPassingRatePerLaunch() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonPassingRatePerLaunch)).click();
    }

    @Step("Click 'Next' button")
    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonNext)).click();
    }

    @Step("Enter launch name: {launchName}")
    public void enterLaunchName(String launchName) {
        wait.until(ExpectedConditions.elementToBeClickable(launchNameField)).sendKeys(launchName);
    }

    @Step("Enter widget name: {widgetName}")
    public void enterWidgetName(String widgetName) {
        wait.until(ExpectedConditions.elementToBeClickable(widgetNameField)).sendKeys(widgetName);
    }

    @Step("Enter description: {description}")
    public void enterDescription(String description) {
        wait.until(ExpectedConditions.elementToBeClickable(descriptionField)).sendKeys(description);
    }

    @Step("Click 'Add' button to create widget")
    public void clickAddButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addWidgetButton)).click();
    }

    @Step("Verify created widget name: {expectedName}")
    public boolean isWidgetCreatedWithName(String expectedName) {
        return wait.until(ExpectedConditions.visibilityOf(createdWidgetName))
                .getText().equals(expectedName);
    }

    public WidgetPage clickButtonFilters() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonFilters)).click();
        return this;
    }

    public WidgetPage clickButtonDashboard() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonDashboard)).click();
        return this;
    }
}