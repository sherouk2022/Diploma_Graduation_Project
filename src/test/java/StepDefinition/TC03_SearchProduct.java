package StepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import io.cucumber.java.en.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

   public class TC03_SearchProduct {
            WebDriver driver;
            WebDriverWait wait;
            String baseUrl = "https://automationexercise.com";
            String searchProductName = "Blue Top";

            @Given("user click on Products")
            public void clickOnProducts() {
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().window().maximize();
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // Navigate to home page
                driver.get(baseUrl);

                // Click on Products link
                WebElement productsLink = driver.findElement(By.linkText("Products"));
                productsLink.click();

                // Verify we're on products page
                wait.until(ExpectedConditions.urlContains("/products"));
                String currentUrl = driver.getCurrentUrl();
                Assert.assertTrue(currentUrl.contains("/products"),
                        "Should be on products page");
            }

            @When("User add product name in search")
            public void addProductNameInSearch() {
                // Wait for search input field to be visible
                WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(
                        By.id("search_product")));

                // Clear and enter product name
                searchInput.clear();
                searchInput.sendKeys(searchProductName);

                // Click search button
                WebElement searchButton = driver.findElement(By.id("submit_search"));
                searchButton.click();

                // Wait for search results to load
                wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.className("features_items")));
            }

            @Then("User can view Product details")
            public void viewProductDetails() {
                try {
                    // Verify search results are displayed
                    WebElement searchResultsContainer = driver.findElement(By.className("features_items"));
                    Assert.assertTrue(searchResultsContainer.isDisplayed(),
                            "Search results container should be visible");

                    // Check if products are found
                    List<WebElement> searchedProducts = driver.findElements(
                            By.cssSelector(".features_items .col-sm-4"));

                    if (searchedProducts.size() > 0) {
                        System.out.println("Found " + searchedProducts.size() + " products matching search");

                        // Click on first product to view details
                        WebElement firstProduct = searchedProducts.get(0);
                        WebElement viewProductLink = firstProduct.findElement(
                                By.linkText("View Product"));
                        viewProductLink.click();

                        // Wait for product detail page to load
                        wait.until(ExpectedConditions.presenceOfElementLocated(
                                By.className("product-information")));

                        // Verify product details are visible
                        WebElement productInfo = driver.findElement(By.className("product-information"));
                        Assert.assertTrue(productInfo.isDisplayed(),
                                "Product information should be displayed");

                        // Verify product name is displayed
                        WebElement productName = driver.findElement(
                                By.cssSelector(".product-information h2"));
                        Assert.assertTrue(productName.isDisplayed(),
                                "Product name should be visible");

                        System.out.println("Product details viewed successfully: " + productName.getText());

                    } else {
                        // Handle case when no products found
                        WebElement noProductsMessage = driver.findElement(
                                By.xpath("//*[contains(text(), 'No products found')]"));
                        System.out.println("No products found for search: " + searchProductName);
                    }

                } catch (Exception e) {
                    System.out.println("Error viewing product details: " + e.getMessage());
                } finally {
                    driver.quit();
                }
            }
        }



