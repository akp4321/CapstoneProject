package stepdefs;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.testng.Assert;
import pages.LoginPage;
import utils.JsonReader;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    @Given("the app is launched and user is on login screen")
    public void the_app_is_launched_and_user_is_on_login_screen() {
        boolean actual = loginPage.loginScreenVisible();
        Assert.assertTrue(actual,"Login screen is not displayed");
    }

    @Given("user login with {string} and {string}")
    public void userLoginWithValidUserAndValidPassword(String userName,String password) {
        loginPage.enterCredentials(userName,password);
    }

    @Given("user login with test data {string}")
    public void user_login_with_test_data(String user) {
        JSONObject testDataInfo = new JSONObject(JsonReader.parse("testData.json").getJSONObject(user).toString());
        String userName = testDataInfo.getString("username");
        String password = testDataInfo.getString("password");
        loginPage.enterCredentials(userName,password);
    }

    @Then("user should see an error message for invalid user")
    public void userShouldSeeAnErrorMessageForInvalidUser() {
        boolean actual = loginPage.invalidLoginCheck();
        Assert.assertTrue(actual,"user is not invalid");
    }

    @Then("user will be logged out successfully")
    public void userWillBeLoggedOutSuccessfully() {
        boolean actual = loginPage.loginScreenVisible();
        Assert.assertTrue(actual,"Login screen is not displayed");
    }

    @When("user clicks login button")
    public void userClicksLoginButton() {
        loginPage.clickLogin();
    }

    @Then("user should see an error message for blank userName")
    public void userShouldSeeAnErrorMessageForBlankUserName() {
        boolean actual = loginPage.blankUserCheck();
        Assert.assertTrue(actual,"user is not blank");
    }

    @Then("user should see an error message for blank password")
    public void userShouldSeeAnErrorMessageForBlankPassword() {
        boolean actual = loginPage.blankPasswordCheck();
        Assert.assertTrue(actual,"password is not blank");
    }

    @Given("user login with no userName {string} and password")
    public void userLoginWithNoUserNameAndPassword(String data) {
        JSONObject testDataInfo = new JSONObject(JsonReader.parse("testData.json").getJSONObject(data).toString());
        String password = testDataInfo.getString("password");
        loginPage.enterUserName("");
        loginPage.enterPassword(password);
    }

    @Given("user login with username {string} and no password")
    public void userLoginWithUsernameAndNoPassword(String data) {
        JSONObject testDataInfo = new JSONObject(JsonReader.parse("testData.json").getJSONObject(data).toString());
        String userName = testDataInfo.getString("username");
        loginPage.enterUserName(userName);
        loginPage.enterPassword("");
    }
}
