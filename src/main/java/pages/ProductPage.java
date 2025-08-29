package pages;

import core.FwkVariables;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProductPage extends BasePage {

    FwkVariables fwkVariables = new FwkVariables();

    private By productPageVisibility() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
        } else {
            return AppiumBy.iOSNsPredicateString("name == \"PRODUCTS\" AND label == \"PRODUCTS\" AND value == \"PRODUCTS\"");
        }
    }

    private By clickMenu() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath(" //*[@class=\"android.view.ViewGroup\" and ./parent::*[@content-desc=\"test-Menu\"]]/*[@class=\"android.widget.ImageView\"]");
        } else {
            return AppiumBy.accessibilityId("test-Menu");
        }
    }

    private By clickLogout() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.widget.TextView[@text=\"LOGOUT\"]");
        } else {
            return AppiumBy.accessibilityId("test-LOGOUT");
        }
    }

    private By checkProductTitle() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.widget.TextView[@content-desc='test-Item title']");
        } else {
            return By.xpath("//XCUIElementTypeStaticText[@name='test-Item title']");

        }
    }

    private By checkProductImage() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("(//android.view.ViewGroup[@content-desc=\"test-Item\"])//android.view.ViewGroup/android.widget.ImageView");
        } else {
            return By.xpath("//XCUIElementTypeOther[@name=\"test-Item\"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeImage");
        }
    }

    private By checkProductPrice() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.widget.TextView[@content-desc=\"test-Price\"]");
        } else {
            return By.xpath("//XCUIElementTypeStaticText[@name=\"test-Price\"]");
        }
    }

    private By bottomPageReached() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.widget.TextView[@text=\"Terms of Service | Privacy Policy\"]");
        } else {
            return By.xpath("//XCUIElementTypeStaticText[@name=\"Terms of Service | Privacy Policy\"]");
        }
    }

    private By selectItem(String productName) {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.widget.TextView[@text='" + productName + "']");
        } else {
            return By.xpath("//XCUIElementTypeStaticText[@name=\"test-Item title\" and @label='" + productName + "']");
        }
    }

    private By selectedItemScreen() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.androidUIAutomator("new UiSelector().text(\"BACK TO PRODUCTS\")");
        } else {
            return AppiumBy.accessibilityId("test-BACK TO PRODUCTS");
        }
    }

    private By selectedItemImage() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.view.ViewGroup[@content-desc=\"test-Image Container\"]/android.widget.ImageView");
        } else {
            return By.xpath("//XCUIElementTypeOther[@name=\"test-Image Container\"]/XCUIElementTypeOther");
        }
    }

    private By selectedItemName(String productName) {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.androidUIAutomator("new UiSelector().text(\""+productName+"\")");
        } else {
            return AppiumBy.accessibilityId(productName);
        }
    }

    private By selectedItemPrice() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.accessibilityId("test-Price");
        } else {
            return AppiumBy.accessibilityId("test-Price");
        }
    }

    private By selectedItemAdding() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.accessibilityId("test-ADD TO CART");
        } else {
            return AppiumBy.accessibilityId("test-ADD TO CART");
        }
    }

    private By addItem(String productName) {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.widget.TextView[@text='" + productName + "']/following-sibling::android.view.ViewGroup[@content-desc='test-ADD TO CART']");
        } else {
            return By.xpath("//XCUIElementTypeOther[@name=\"test-Item\" and contains(@label,'"+productName+"')]/XCUIElementTypeOther//XCUIElementTypeOther[contains(@name,\" ADD TO CART\")]//XCUIElementTypeOther[@name=\"ADD TO CART\"]");
        }
    }

    private By removeItem(String productName) {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.widget.TextView[@text='" + productName + "']/ancestor::android.view.ViewGroup[@content-desc='test-Item']//android.widget.TextView[@text='REMOVE']");
        } else {
            return By.xpath("//XCUIElementTypeOther[@name=\"test-Item\" and contains(@label,'"+productName+"')]//XCUIElementTypeOther[contains(@name,\"REMOVE\")]//XCUIElementTypeOther[@name=\"REMOVE\"]");
        }
    }

    private By cartBadgeCount() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return By.xpath("//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup/android.widget.TextView");
        } else {
            return By.xpath("//XCUIElementTypeOther[@name=\"test-Cart\"]/XCUIElementTypeOther");
        }
    }

    private By cartIcon() {
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            return AppiumBy.accessibilityId("test-Cart");
        } else {
            return AppiumBy.accessibilityId("test-Cart");
        }
    }




    public boolean loginSuccessful() {
        return isElementDisplayed(productPageVisibility());
    }

    public void clickMenuButton() {
        isElementDisplayed(clickMenu());
        waitForElementClickable(clickMenu(), 20);
        if(platform.equalsIgnoreCase("Android")) {
            click(clickMenu());
        } else if (platform.equalsIgnoreCase("ios")) {
            tapOnElement(findElement(clickMenu()));
        }
    }

    public void clickLogoutButton() {
        waitForElementClickable(clickLogout(), 20);
        click(clickLogout());
    }

    public boolean allProductsAreVisible() {
        List<WebElement> titles;
        List<WebElement> prices = new ArrayList<>();
        List<WebElement> images = new ArrayList<>();
        List<WebElement> bottomElement;
        List<WebElement> mainList_titles = new ArrayList<>();
        boolean flag = false;
        int maxScrolls = 20;
        Set<WebElement> titlesSet = new HashSet<>();
        try {
            for (int i = 0; i < maxScrolls; i++) {
                titles = findElements(checkProductTitle());
                prices = findElements(checkProductPrice());
                images = findElements(checkProductImage());
                mainList_titles.addAll(titles);
                titlesSet.addAll(mainList_titles);
                scrolling("down");
                titles = findElements(checkProductTitle());
                mainList_titles.addAll(titles);
                titlesSet.addAll(mainList_titles);
                bottomElement = findElements(bottomPageReached());

                if (bottomElement.size()>0) {
                    System.out.println(bottomElement.get(0).getText());
                    break;
                }
            }
            if(!titlesSet.isEmpty() && !prices.isEmpty() && !images.isEmpty()) {
                flag = true;
            }
            System.out.println("Total unique elements present on page: " + titlesSet.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    public void addItemToCart(String productName) {
        try {
            if (isElementDisplayed(addItem(productName))) {
                findElement(addItem(productName)).click();
            } else {
                int count = 5;
                while (count > 0) {
                    scrollToElement(addItem(productName),5);
                    count--;
                    if (isElementDisplayed(addItem(productName))) {
                        findElement(addItem(productName)).click();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void removeItemFromCart(String productName) {
        try {
            if (isElementDisplayed(addItem(productName))) {
                findElement(removeItem(productName)).click();
            } else {
                int count = 5;
                while (count > 0) {
                    scrollToElement(removeItem(productName),5);
                    count--;
                    if (isElementDisplayed(removeItem(productName))) {
                        findElement(removeItem(productName)).click();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getCartBadgeCount() {
        try {
            return findElement(cartBadgeCount()).getText().trim();
        } catch (Exception e) {
            return "0";
        }
    }

    public void clickCartIcon() {
        isElementDisplayed(cartIcon());
        waitForElementClickable(clickMenu(), 20);
        if(platform.equalsIgnoreCase("Android")) {
            click(cartIcon());
        } else if (platform.equalsIgnoreCase("ios")) {
            tapOnElement(findElement(cartIcon()));
        }
    }

    public void tapProduct(String productName) {
        click(selectItem(productName));
    }

    public boolean productDetailsScreenVisibility(String productName, String itemPrice) {
        boolean flag = false;
        boolean title = isElementDisplayed(selectedItemScreen());
        boolean image = isElementDisplayed(selectedItemImage());
        boolean itemName = isElementDisplayed(selectedItemName(productName));
        scrollToElement(selectedItemPrice(),5);
        boolean price = isElementDisplayed(selectedItemPrice());
        String priceValue = findElement(selectedItemPrice()).getText().trim();
        Assert.assertEquals(priceValue,itemPrice,"Price is not same");
        scrollToElement(selectedItemAdding(),5);
        boolean addToCart = isElementDisplayed(selectedItemAdding());
        if (title && image && itemName && price && addToCart) {
            flag = true;
        }
        return flag;
    }
}
