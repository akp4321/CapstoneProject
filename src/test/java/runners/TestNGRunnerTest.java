package runners;



import core.FwkVariables;
import core.ThreadLocalDriverManager;
import io.cucumber.testng.*;
import org.json.JSONObject;
import org.testng.annotations.*;
import utils.JsonReader;



@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefs","hooks"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        tags = "@prod"
)
public class TestNGRunnerTest {

    private TestNGCucumberRunner testNGCucumberRunner;

    @Parameters("deviceName")
    @BeforeClass(alwaysRun = true)
    public void setupClass(String deviceName) throws Exception {
        System.out.println("Starting test on thread: " + Thread.currentThread().getId() +
                ", device: " + deviceName);
        JSONObject deviceInfo = new JSONObject(JsonReader.parse("deviceConfig.json").getJSONObject(deviceName).toString());
        FwkVariables fwkVariables = new FwkVariables();
        fwkVariables.setDeviceName(deviceInfo.getString("deviceName"));
        fwkVariables.setPlatformName(deviceInfo.getString("platformName"));
        fwkVariables.setPlatformVersion(deviceInfo.getString("platformVersion"));
        fwkVariables.setApp(deviceInfo.getString("app"));

        ThreadLocalDriverManager.initializeDriver(deviceName);
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable{
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @DataProvider()
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        ThreadLocalDriverManager.quitDriver();
        if (testNGCucumberRunner != null) {
            testNGCucumberRunner.finish();
        }

    }

}

