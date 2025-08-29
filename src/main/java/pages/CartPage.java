package pages;

import core.FwkVariables;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CartPage extends BasePage {

    FwkVariables fwkVariables = new FwkVariables();

    private By cartPageVisibility() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.androidUIAutomator("new UiSelector().text(\"YOUR CART\")");
        } else {
            return AppiumBy.iOSNsPredicateString("name == \"YOUR CART\" AND label == \"YOUR CART\" AND value == \"YOUR CART\"");
        }
    }

    private By productName(String productName) {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.androidUIAutomator("new UiSelector().text(\""+productName+"\")");
        } else {
            return AppiumBy.accessibilityId(productName);
        }
    }

    private By continueShopping() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.accessibilityId("test-CONTINUE SHOPPING");
        } else {
            return AppiumBy.accessibilityId("test-CONTINUE SHOPPING");
        }
    }


    private By productPrice(String name) {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.widget.TextView[@text='"+ name +"']/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup//android.widget.TextView[contains(@text, '$')]");
        } else {
            return By.xpath("//XCUIElementTypeOther[@name=\"test-Item\" and contains(@label,'"+name+"')]//XCUIElementTypeOther[@name=\"test-Price\" and contains(@label,'$')]/XCUIElementTypeStaticText");
        }
    }

    private By productQuantity(String name) {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.widget.TextView[@text='" + name + "']/parent::android.view.ViewGroup/preceding-sibling::android.view.ViewGroup/android.widget.TextView");
        } else {
            return By.xpath("//XCUIElementTypeOther[@name=\"test-Item\" and contains(@label,'"+name+"')]//XCUIElementTypeOther[@name=\"test-Amount\"]");
        }
    }

    private By checkoutButton() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.accessibilityId("test-CHECKOUT");
        } else {
            return AppiumBy.accessibilityId("CHECKOUT");
        }
    }

    public boolean cartPageLoaded() {
        return isElementDisplayed(cartPageVisibility());
    }

    public boolean productNameVisible(String productName) {
        return isElementDisplayed(productName(productName));
    }

    public void clickContinueShoppingButton() {
        if (isElementDisplayed(continueShopping())) {
            click(continueShopping());
        }
        else {
            scrollToElement(continueShopping(),5);
            click(continueShopping());
        }


    }

    public void assertItemVisible(String productName, String expectedPrice, String expectedQty) {
        try {
            scrollToElement(productName(productName),5);
            Assert.assertTrue(isElementDisplayed(productName(productName)), "Product not found: " + productName);
            scrollToElement(productPrice(productName),5);
            String actualPrice = findElement(productPrice(productName)).getText().trim();
            Assert.assertEquals(expectedPrice, actualPrice, "Price mismatch for: " + productName);
            scrollToElement(productQuantity(productName),5);
            String actualQty = findElement(productQuantity(productName)).getText().trim();
            Assert.assertEquals(expectedQty, actualQty, "Quantity mismatch for: " + productName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void tapCheckoutButton() {
        click(checkoutButton());
    }
}
