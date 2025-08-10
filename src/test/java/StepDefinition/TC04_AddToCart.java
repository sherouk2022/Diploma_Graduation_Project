package StepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.List;
import java.util.Map;

        public class TC04_AddToCart {
        private ProductsPage productsPage;
        private CartPage cartPage;
        private List<Map<String, String>> addedProducts;

        @Given("User open products' page")
        public void user_open_products_page() {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                driver.get("https://automationexercise.com/products");
            }
            productsPage = new ProductsPage(driver);

        // Verify we're on the product page
        Assert.assertTrue(productsPage.isProductsPageLoaded(),
        "Products page should be loaded");
        }

        @When("user can select more than product and add them to the cart")
        public void user_can_select_more_than_product_and_add_them_to_the_cart() {
        // Add multiple products to cart
        addedProducts = productsPage.addMultipleProductsToCart(2);

        // Verify products were added
            assert addedProducts != null;
            Assert.assertTrue(addedProducts.size() >= 2,
        "At least 2 products should be added");
        }

        @And("user can view the Cart")
        public void user_can_view_the_cart() {
        cartPage = productsPage.goToCart();

        // Verify we're on cart page
        Assert.assertTrue(cartPage.isCartPageLoaded(),
        "Cart page should be loaded");
        }

        @Then("Verify the Products' prices, quantity and total price")
        public void verify_the_products_prices_quantity_and_total_price() {
        // Get cart items
        List<Map<String, String>> cartItems = cartPage.getCartItems();

        // Verify the number of items in cart matches added products
            assert cartItems != null;
            Assert.assertEquals(cartItems.size(), addedProducts.size(),
        "Cart should contain the same number of products as added");

        double expectedTotal = 0;

        // Verify each product detail
        for (int i = 0; i < addedProducts.size(); i++) {
        Map<String, String> addedProduct = addedProducts.get(i);
        Map<String, String> cartItem = cartItems.get(i);

        // Verify product name
        Assert.assertEquals(cartItem.get("name"), addedProduct.get("name"),
        "Product name should match");

        // Verify price
        Assert.assertEquals(cartItem.get("price"), addedProduct.get("price"),
        "Product price should match");

        // Verify quantity (default is 1)
        Assert.assertEquals(cartItem.get("quantity"), "1",
        "Product quantity should be 1");

        // Calculate the expected total
        double price = Double.parseDouble(addedProduct.get("price").replace("Rs. ", ""));
        expectedTotal += price;
        }

        // Verify total price
        double actualTotal = cartPage.getTotalPrice();
        Assert.assertEquals(actualTotal, expectedTotal, 0.01,
        "Total price should match sum of individual prices");

        System.out.println("Cart verification completed successfully!");
        System.out.println("Total products: " + cartItems.size());
        System.out.println("Total amount: Rs. " + actualTotal);
        }

            private static class CartPage {
                public double getTotalPrice() {
                    return 0;
                }

                public List<Map<String, String>> getCartItems() {
                    return null;
                }

                public boolean isCartPageLoaded() {
                    return false;
                }
            }

            private static class DriverManager {
                public static WebDriver getDriver() {
                    return null;
                }
            }

            private static class ProductsPage {
                public ProductsPage(WebDriver driver) {

                }

                public boolean isProductsPageLoaded() {
                    return false;
                }

                public List<Map<String, String>> addMultipleProductsToCart(int i) {
                    return null;
                }

                public CartPage goToCart() {
                    return null;
                }
            }
        }
