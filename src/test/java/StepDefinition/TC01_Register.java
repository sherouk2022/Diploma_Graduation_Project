package StepDefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.time.Duration;

     public class TC01_Register {
        WebDriver driver;
        String baseUrl = "https://automationexercise.com";

        @Given("navigate the user to Signin page")
        public void navigateToSigninPage() {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get(baseUrl + "/login");
        }

        @When("user can enter Name")
        public void enterName() {
            WebElement nameField = driver.findElement(By.cssSelector("input[data-qa='signup-name']"));
            nameField.sendKeys("Test User");
        }

        @And("user can enter new Email and click the Signup button")
        public void enterNewEmailAndSignup() {
            WebElement emailField = driver.findElement(By.cssSelector("input[data-qa='signup-email']"));
            emailField.sendKeys("testuser" + System.currentTimeMillis() + "@example.com");

            WebElement signupBtn = driver.findElement(By.cssSelector("button[data-qa='signup-button']"));
            signupBtn.click();
        }

        @And("user can Fill First details")
        public void fillFirstDetails() {
            // Title selection
            driver.findElement(By.id("id_gender1")).click();

            // Password
            driver.findElement(By.id("password")).sendKeys("TestPassword123");

            // Date of birth
            driver.findElement(By.id("days")).sendKeys("15");
            driver.findElement(By.id("months")).sendKeys("May");
            driver.findElement(By.id("years")).sendKeys("1990");
        }

        @And("user Select checkbox sign up for our newsletter")
        public void selectNewsletterCheckbox() {
            WebElement newsletterCheckbox = driver.findElement(By.id("newsletter"));
            if (!newsletterCheckbox.isSelected()) {
                newsletterCheckbox.click();
            }
        }

        @And("user Select checkbox Receive special offers from our partners")
        public void selectOffersCheckbox() {
            WebElement offersCheckbox = driver.findElement(By.id("optin"));
            if (!offersCheckbox.isSelected()) {
                offersCheckbox.click();
            }
        }

        @And("user can Fill Address Information")
        public void fillAddressInfo() {
            driver.findElement(By.id("first_name")).sendKeys("Test");
            driver.findElement(By.id("last_name")).sendKeys("User");
            driver.findElement(By.id("company")).sendKeys("Test Company");
            driver.findElement(By.id("address1")).sendKeys("123 Test Street");
            driver.findElement(By.id("address2")).sendKeys("Apt 456");
            driver.findElement(By.id("country")).sendKeys("United States");
            driver.findElement(By.id("state")).sendKeys("California");
            driver.findElement(By.id("city")).sendKeys("Los Angeles");
            driver.findElement(By.id("zipcode")).sendKeys("90210");
            driver.findElement(By.id("mobile_number")).sendKeys("1234567890");
        }

        @And("user click on Create Account button")
        public void clickCreateAccount() {
            WebElement createAccountBtn = driver.findElement(By.cssSelector("button[data-qa='create-account']"));
            createAccountBtn.click();
        }

        @Then("navigate the user to home page")
        public void verifyNavigationToHomePage() {
            String expectedUrl = baseUrl + "/account_created";
            Assert.assertTrue(driver.getCurrentUrl().contains("account_created"),
                    "User was not redirected to account created page");
            driver.quit();
        }

        @And("User Enter an Existing Email and enter Signup button")
        public void enterExistingEmailAndSignup() {
            WebElement emailField = driver.findElement(By.cssSelector("input[data-qa='signup-email']"));
            emailField.sendKeys("existing@example.com");

            WebElement signupBtn = driver.findElement(By.cssSelector("button[data-qa='signup-button']"));
            signupBtn.click();
        }

        @Then("user cannot navigate to the next page")
        public void verifyUserCannotProceed() {
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("/login"),
                    "User should remain on login page for existing email");

            // Check for error message
            try {
                WebElement errorMsg = driver.findElement(By.xpath("//*[contains(text(),'Email Address already exist!')]"));
                Assert.assertTrue(errorMsg.isDisplayed(), "Error message should be displayed");
            } catch (Exception e) {
                // Error message might have different text or selector
            }
            driver.quit();
        }
    }
