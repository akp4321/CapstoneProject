package hooks;


import core.ThreadLocalDriverManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class Hooks {
    AppiumDriver driver = ThreadLocalDriverManager.getDriver();
    @AfterStep
    public void takeScreenshotAfterEachStep(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take screenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

            // Attach to Cucumber report (if using Cucumber Reports)
            scenario.attach(screenshot, "image/png", "Failure Screenshot");

            // Optionally save to local disk
            try {
                File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
                Files.copy(
                        screenshotFile.toPath(),
                        Paths.get("screenshots", scenario.getName().replaceAll(" ", "_") + ".png"),
                        StandardCopyOption.REPLACE_EXISTING
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


