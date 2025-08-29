package stepdefs;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.testng.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import utils.JsonReader;

import java.util.List;
import java.util.Map;

public class CheckoutSteps {

    CheckoutPage checkoutPage = new CheckoutPage();

    @Then("user will navigate to checkout page")
    public void userWillNavigateToCartPage() {
        Assert.assertTrue(checkoutPage.checkoutPageLoaded(),"Checkout Page is not loaded");
    }


    @Then("error message should be {string}")
    public void errorMessageShouldBe(String message) {
        String actual = checkoutPage.getErrorMessage().trim();
        Assert.assertEquals(actual,message,"Error message is different");
    }

    @When("user submits the checkout form")
    public void userSubmitsTheCheckoutForm() {
        checkoutPage.clickContinueButton();
    }

    @When("user cancels the checkout form")
    public void userCancelsTheCheckoutForm() {
        checkoutPage.clickCancelButton();
    }

    @When("user enters checkout details {string} with missing lastName")
    public void userEntersCheckoutDetailsWithMissingLastName(String checkoutDetails) {
        JSONObject testDataInfo = new JSONObject(JsonReader.parse("testData.json").getJSONObject(checkoutDetails).toString());
        String firstName = testDataInfo.getString("firstName");
        String postalCode = testDataInfo.getString("postalCode");
        checkoutPage.fillCheckoutForm(firstName,"",postalCode);
    }

    @When("user enters checkout details {string} with missing postalCode")
    public void userEntersCheckoutDetailsWithMissingPostalCode(String checkoutDetails) {
        JSONObject testDataInfo = new JSONObject(JsonReader.parse("testData.json").getJSONObject(checkoutDetails).toString());
        String firstName = testDataInfo.getString("firstName");
        String lastName = testDataInfo.getString("lastName");
        checkoutPage.fillCheckoutForm(firstName,lastName,"");
    }

    @When("user enters valid checkout details {string}")
    public void userEntersValidCheckoutDetails(String checkoutDetails) {
        JSONObject testDataInfo = new JSONObject(JsonReader.parse("testData.json").getJSONObject(checkoutDetails).toString());
        String firstName = testDataInfo.getString("firstName");
        String lastName = testDataInfo.getString("lastName");
        String postalCode = testDataInfo.getString("postalCode");
        checkoutPage.fillCheckoutForm(firstName,lastName,postalCode);
    }

    @Then("user should be navigated to the overview screen")
    public void userShouldBeNavigatedToTheOverviewScreen() {
        Assert.assertTrue(checkoutPage.overviewPageLoaded(),"Checkout Overview Page is not loaded");
    }

    @When("user finishes the checkout")
    public void userFinishesTheCheckout() {
        checkoutPage.tapFinishButton();
    }

    @Then("user should be navigated to the checkout complete screen")
    public void userShouldBeNavigatedToTheCheckoutCompleteScreen() {
        Assert.assertTrue(checkoutPage.checkoutCompletePageLoaded(),"Checkout Complete Page is not loaded");
    }

    @And("confirmation message should be {string}")
    public void confirmationMessageShouldBe(String message) {
        String actual = checkoutPage.getConfirmationMessageText();
        Assert.assertEquals(actual,message,"Confirmation message is different");
    }
}