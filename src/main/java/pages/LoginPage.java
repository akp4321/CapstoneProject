package pages;

import core.FwkVariables;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;


public class LoginPage extends BasePage{

    FwkVariables fwkVariables = new FwkVariables();
    private By usernameField() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.androidUIAutomator("new UiSelector().description(\"test-Username\")");
        } else {
            return AppiumBy.accessibilityId("test-Username");
        }
    }

    private By passwordField() {
        return platform.equalsIgnoreCase("android")
                ? AppiumBy.androidUIAutomator("new UiSelector().description(\"test-Password\")")
                : AppiumBy.accessibilityId("test-Password");
    }

    private By loginButton() {
        return platform.equalsIgnoreCase("android")
                ? AppiumBy.androidUIAutomator("new UiSelector().text(\"LOGIN\")")
                : AppiumBy.accessibilityId("test-LOGIN");
    }

    private By invalidCredCheck() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]");
        } else {
            return AppiumBy.iOSNsPredicateString("name == \"Username and password do not match any user in this service.\"");
        }
    }

    private By blankUserNameCheck() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.widget.TextView[@text=\"Username is required\"]");
        } else {
            return AppiumBy.iOSNsPredicateString("name == \"Username is required\"");
        }
    }

    private By blankPwdCheck() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.widget.TextView[@text=\"Password is required\"]");
        } else {
            return AppiumBy.iOSNsPredicateString("name == \"Password is required\"");
        }
    }

    public void enterCredentials(String userName, String password) {
        sendKeys(usernameField(),userName);
        sendKeys(passwordField(),password);
    }

    public void enterUserName(String userName) {
        sendKeys(usernameField(),userName);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField(),password);
    }

    public boolean loginScreenVisible() {
        return isElementDisplayed(loginButton());
    }

    public void clickLogin() {
        click(loginButton());
    }

    public boolean invalidLoginCheck() {
        return isElementDisplayed(invalidCredCheck());
    }

    public boolean blankUserCheck() {
        return isElementDisplayed(blankUserNameCheck());
    }

    public boolean blankPasswordCheck() {
        return isElementDisplayed(blankPwdCheck());
    }


}
