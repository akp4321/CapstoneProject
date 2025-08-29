package stepdefs;



import core.FwkVariables;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.testng.Assert;
import pages.ProductPage;
import utils.JsonReader;
import java.util.List;

public class ProductSteps {

    ProductPage productPage = new ProductPage();
    @Then("user should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        boolean actual = productPage.loginSuccessful();
        Assert.assertTrue(actual,"Login is not successful");
    }

    @When("user clicks on menu button")
    public void userClicksOnMenuButton() {
        productPage.clickMenuButton();
    }

    @And("user clicks logout")
    public void userClicksLogout() {
        productPage.clickLogoutButton();
    }

    @Given("user is on product page")
    public void userIsOnProductPage() {
        boolean actual = productPage.loginSuccessful();
        Assert.assertTrue(actual,"User is not on product page");
    }

    @Then("user confirms all items are loaded properly")
    public void userConfirmsAllItemsLoaded() {
        Assert.assertTrue(productPage.allProductsAreVisible(),"All products should be visible");
    }

    @When("user click on the cart icon")
    public void userClickOnTheCartIcon() {
        productPage.clickCartIcon();
    }

    @When("user remove the added item {string}")
    public void userRemoveTheAddedItem(String productName) {
        JSONObject testDataInfo = new JSONObject(JsonReader.parse("testData.json").getJSONObject(productName).toString());
        String name = testDataInfo.getString("name");
        productPage.removeItemFromCart(name);
    }

    @When("user will add one item to cart {string}")
    public void userWillAddOneItemToCart(String productName) {
        JSONObject testDataInfo = new JSONObject(JsonReader.parse("testData.json").getJSONObject(productName).toString());
        String name = testDataInfo.getString("name");
        productPage.addItemToCart(name);
    }

    @Then("cart icon will update to show {string}")
    public void cartIconWillUpdateToShow(String number) {
        String actual = productPage.getCartBadgeCount();
        Assert.assertEquals(actual,number,"Value is different");
    }

    @When("the user adds the following items to the cart:")
    public void theUserAddsTheFollowingItemsToTheCart(List<String> products) {
        for (String item : products) {
            productPage.addItemToCart(item);
        }
    }

    @When("the user removes the same items from the cart:")
    public void theUserRemovesTheSameItemsFromTheCart(List<String> products) {
        for (String item : products) {
            productPage.removeItemFromCart(item);
        }
    }

    @When("user taps on a product {string}")
    public void userTapsOnAProduct(String productName) {
        JSONObject testDataInfo = new JSONObject(JsonReader.parse("testData.json").getJSONObject(productName).toString());
        String name = testDataInfo.getString("name");
        productPage.tapProduct(name);
    }

    @Then("user should see the product detail screen with item name {string}")
    public void userShouldSeeTheProductDetailScreenWithItemName(String productName) {
        JSONObject testDataInfo = new JSONObject(JsonReader.parse("testData.json").getJSONObject(productName).toString());
        String name = testDataInfo.getString("name");
        String price = testDataInfo.getString("price");
        boolean actual = productPage.productDetailsScreenVisibility(name,price);
        Assert.assertTrue(actual,"Product detail screen is not visible");
    }

    @Then("cart icon will not have any value")
    public void cartIconWillNotHaveAnyValue() {
        FwkVariables fwkVariables = new FwkVariables();
        String actual = productPage.getCartBadgeCount();
        if (fwkVariables.getPlatformName().equalsIgnoreCase("Android")) {
            Assert.assertEquals(actual,"0","Cart Value is not 0");
        } else if (fwkVariables.getPlatformName().equalsIgnoreCase("ios")) {
            Assert.assertEquals(actual,"","Cart Value is not 0");
        }

    }
}