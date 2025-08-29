package pages;

import core.FwkVariables;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;


public class CheckoutPage extends BasePage {

    FwkVariables fwkVariables = new FwkVariables();

    private By checkoutPageTitle() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.androidUIAutomator("new UiSelector().text(\"CHECKOUT: INFORMATION\")");
        } else {
            return AppiumBy.iOSNsPredicateString("name == \"CHECKOUT: INFORMATION\" AND label == \"CHECKOUT: INFORMATION\" AND value == \"CHECKOUT: INFORMATION\"");
        }
    }

    private By continueButton() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.androidUIAutomator("new UiSelector().text(\"CONTINUE\")");
        } else {
            return AppiumBy.accessibilityId("test-CONTINUE");
        }
    }

    private By cancelButton() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.androidUIAutomator("new UiSelector().text(\"CANCEL\")");
        } else {
            return AppiumBy.accessibilityId("test-CANCEL");
        }
    }

    private By errorMessageText() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");
        } else {
            return By.xpath("//XCUIElementTypeOther[@name=\"test-Error message\"]/XCUIElementTypeStaticText");
        }
    }

    private By firstNameField() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.accessibilityId("test-First Name");
        } else {
            return AppiumBy.accessibilityId("test-First Name");
        }
    }

    private By lastNameField() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.accessibilityId("test-Last Name");
        } else {
            return AppiumBy.accessibilityId("test-Last Name");
        }
    }

    private By postalCodeField() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.accessibilityId("test-Zip/Postal Code");
        } else {
            return AppiumBy.accessibilityId("test-Zip/Postal Code");
        }
    }

    private By overviewPageTitle() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.androidUIAutomator("new UiSelector().text(\"CHECKOUT: OVERVIEW\")");
        } else {
            return AppiumBy.iOSNsPredicateString("name == \"CHECKOUT: OVERVIEW\" AND label == \"CHECKOUT: OVERVIEW\" AND value == \"CHECKOUT: OVERVIEW\"");
        }
    }

    private By finishButton() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.accessibilityId("test-FINISH");
        } else {
            return AppiumBy.accessibilityId("test-FINISH");
        }
    }

    private By checkoutCompletePageTitle() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.androidUIAutomator("new UiSelector().text(\"CHECKOUT: COMPLETE!\")");
        } else {
            return AppiumBy.iOSNsPredicateString("name == \"CHECKOUT: COMPLETE!\" AND label == \"CHECKOUT: COMPLETE!\" AND value == \"CHECKOUT: COMPLETE!\"");
        }
    }

    private By confirmationMessageText() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.androidUIAutomator("new UiSelector().text(\"THANK YOU FOR YOU ORDER\")");
        } else {
            return AppiumBy.iOSNsPredicateString("name == \"THANK YOU FOR YOU ORDER\"");
        }
    }


    public boolean checkoutPageLoaded() {
        return isElementDisplayed(checkoutPageTitle());
    }

    public void clickContinueButton() {
        click(continueButton());
    }

    public void clickCancelButton() {
        click(cancelButton());
    }

    public String getErrorMessage() {
        return findElement(errorMessageText()).getText().trim();
    }

    public void fillCheckoutForm(String firstName, String lastName, String zipCode) {
        if (firstName != null && !firstName.isEmpty()) {
            sendKeys(firstNameField(),firstName);
        }

        if (lastName != null && !lastName.isEmpty()) {
            sendKeys(lastNameField(),lastName);
        }

        if (zipCode != null && !zipCode.isEmpty()) {
            sendKeys(postalCodeField(),zipCode);
        }
    }

    public boolean overviewPageLoaded() {
        return isElementDisplayed(overviewPageTitle());
    }

    public void tapFinishButton() {
        scrollToElement(finishButton(),5);
        click(finishButton());
    }

    public boolean checkoutCompletePageLoaded() {
        return isElementDisplayed(checkoutCompletePageTitle());
    }

    public String getConfirmationMessageText() {
        return findElement(confirmationMessageText()).getText();
    }
}
