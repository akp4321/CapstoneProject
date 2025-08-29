package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.JsonReader;
import utils.PropertyReader;


import java.net.URL;
import java.util.Properties;

public class ThreadLocalDriverManager {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(AppiumDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void initializeDriver(String deviceName) throws Exception {
        AppiumDriver driverLocal = null;
        JSONObject deviceInfo = new JSONObject(JsonReader.parse("deviceConfig.json").getJSONObject(deviceName).toString());
        String platformName = deviceInfo.getString("platformName");
        DesiredCapabilities capabilities = CapabilityManger.getCapability(deviceName);
        Properties props = new PropertyReader().getProperty();
        if (driverLocal == null) {
            try {
                switch (platformName) {
                    case "android":
                        driverLocal = new AndroidDriver(new URL("https://" +props.getProperty("userName") + ":" + props.getProperty("accessKey") + "@mobile-hub.lambdatest.com/wd/hub"), capabilities);
                        break;

                    case "ios":
                        driverLocal = new IOSDriver(new URL("https://" +props.getProperty("userName") + ":" + props.getProperty("accessKey") + "@mobile-hub.lambdatest.com/wd/hub"), capabilities);
                        break;
                }
                driver.set(driverLocal);
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void removeDriver() {
        if (driver.get() != null) {
            driver.remove();
        }
    }
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}
