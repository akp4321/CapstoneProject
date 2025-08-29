package stepdefs;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.testng.Assert;
import pages.CartPage;
import pages.ProductPage;
import utils.JsonReader;

import java.util.List;
import java.util.Map;

public class CartSteps {

    CartPage cartPage = new CartPage();

    @Then("user will navigate to cart page")
    public void userWillNavigateToCartPage() {
        Assert.assertTrue(cartPage.cartPageLoaded(),"Cart Page is not loaded");
    }

    @And("added {string} should be present in cart")
    public void addedShouldBePresentInCart(String productName) {
        JSONObject testDataInfo = new JSONObject(JsonReader.parse("testData.json").getJSONObject(productName).toString());
        String name = testDataInfo.getString("name");
        Assert.assertTrue(cartPage.productNameVisible(name),"Product Name is not present");
    }

    @When("user clicks continue to shopping button")
    public void userClicksContinueToShoppingButton() {
        cartPage.clickContinueShoppingButton();
    }

    @Then("the cart should contain the following items:")
    public void theCartShouldContainTheFollowingItems(DataTable dataTable) {

        List<Map<String, String>> expectedItems = dataTable.asMaps();
        for (Map<String, String> item : expectedItems) {
            String name = item.get("Product Name");
            String quantity = item.get("Quantity");
            String price = item.get("Price");
            cartPage.assertItemVisible(name, price, quantity);
            }
    }

    @And("user click checkout button to proceed")
    public void userClickCheckoutButtonToProceed() {
        cartPage.tapCheckoutButton();
    }
}