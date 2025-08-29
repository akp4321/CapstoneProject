package pages;

import core.ThreadLocalDriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;


public class BasePage {
    protected AppiumDriver driver;
    protected String platform;
    private final int DEFAULT_TIMEOUT = 10;
    protected WebDriverWait wait;
    int timeoutInSeconds;
    public BasePage(){
        this.driver = ThreadLocalDriverManager.getDriver();
        this.platform = ThreadLocalDriverManager.getDriver().getCapabilities().getPlatformName().toString();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
    }

    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public WebElement waitForElementVisible(By locator, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(this.driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be clickable
    public WebElement waitForElementClickable(By locator, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(this.driver, Duration.ofSeconds(timeoutInSeconds));
        return customWait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementEnabled(By locator) {
        try {
            return driver.findElement(locator).isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void tapOnElement(WebElement element) {
        // Get element center
        int startX = element.getLocation().getX();
        int startY = element.getLocation().getY();
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();

        int tapX = startX + width - 1;
        int tapY = startY + height - 1;


        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);

        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), tapX, tapY));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap));
    }

    public void sendKeys(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    public void click(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }


    public void swipeUp(int durationInMillis) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        performSwipe(startX, startY, startX, endY, durationInMillis);
    }

    private void performSwipe(int startX, int startY, int endX, int endY, int durationInMillis) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(durationInMillis), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    public void swipeDown(int durationInMillis) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.2);
        int endY = (int) (size.height * 0.8);

        performSwipe(startX, startY, startX, endY, durationInMillis);
    }

    public void swipeLeft(int durationInMillis) {
        Dimension size = driver.manage().window().getSize();
        int startY = size.height / 2;
        int startX = (int) (size.width * 0.8);
        int endX = (int) (size.width * 0.2);

        performSwipe(startX, startY, endX, startY, durationInMillis);
    }

    public void swipeRight(int durationInMillis) {
        Dimension size = driver.manage().window().getSize();
        int startY = size.height / 2;
        int startX = (int) (size.width * 0.2);
        int endX = (int) (size.width * 0.8);

        performSwipe(startX, startY, endX, startY, durationInMillis);
    }

    public boolean scrollDown() {
        if (platform.equalsIgnoreCase("iOS")) {
            Map<String, Object> scroll = new HashMap<>();
            scroll.put("direction", "down");
            driver.executeScript("mobile: scroll", scroll);
        } else {

            Dimension size = driver.manage().window().getSize();
            int startY = (int) (size.height * 0.8);
            int endY = (int) (size.height * 0.2);
            int startX = size.width / 2;

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), startX, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Collections.singletonList(swipe));
        }
        return true;
    }

    public boolean scrollUntilElementVisible(By locator, int maxScrolls, String direction) {
        int scrollCount = 0;
        while (scrollCount < maxScrolls) {
            List<WebElement> elements = driver.findElements(locator);
            if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                return true;
            }
            if (direction.equalsIgnoreCase("down")) {
                scrolling("down");
            } else if (direction.equalsIgnoreCase("up")) {
                scrolling("up");
            }
            scrollCount++;
        }
        throw new RuntimeException("❌ Element not found after " + maxScrolls + " scrolls");
    }

    public void scrollUp() {
        Dimension size = driver.manage().window().getSize();

        int screenWidth = size.getWidth();
        int screenHeight = size.getHeight();

        int startX = screenWidth / 2;
        int startY = (int) (screenHeight * 0.2); // near bottom
        int endY = (int) (screenHeight * 0.8);   // near top

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        this.driver.perform(Arrays.asList(swipe));
    }

    public void scrolling (String direction) {
        Dimension size = driver.manage().window().getSize();

        int screenWidth = size.getWidth();
        int screenHeight = size.getHeight();

        int startX = screenWidth / 2;
        int startY = 0;
        int endY = 0;

        if (direction.equalsIgnoreCase("down")) {
            startY = (int) (screenHeight * 0.7);  // swipe from bottom to top
            endY = (int) (screenHeight * 0.3);
        } else if (direction.equalsIgnoreCase("up")) { // DOWN
            startY = (int) (screenHeight * 0.3);  // swipe from top to bottom
            endY = (int) (screenHeight * 0.7);
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    public boolean scrollToElement(By locator, int maxScrolls) {
        // First, try scrolling down
        for (int i = 0; i < maxScrolls; i++) {
            if (isElementDisplayed(locator)) {
                return true;
            }
            scrolling("down");
        }

        // Then, try scrolling up (in case the element is above)
        for (int i = 0; i < maxScrolls; i++) {
            if (isElementDisplayed(locator)) {
                return true;
            }
            scrolling("up");
        }

        return false;
    }

}