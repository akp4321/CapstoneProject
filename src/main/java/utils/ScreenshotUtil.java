package utils;

import core.ThreadLocalDriverManager;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String takeScreenshot(String stepName) {
        AppiumDriver driver = ThreadLocalDriverManager.getDriver();
        if (driver == null) {
            throw new IllegalStateException("Appium driver is not initialized for this thread.");
        }

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
        String filePath = System.getProperty("user.dir") + "/screenshots/" + stepName + "_" + timestamp + ".png";
        File dest = new File(filePath);

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }
}
