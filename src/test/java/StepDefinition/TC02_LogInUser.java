package StepDefinition;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.time.Duration;

public class TC02_LogInUser {
    WebDriver driver;
    String baseUrl = "https://automationexercise.com";

    @Given("user can Click on Signup or Login button")
    public void clickSignupLoginButton() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(baseUrl);

        // Click on Signup/Login link in header
        WebElement signupLoginLink = driver.findElement(By.linkText("Signup / Login"));
        signupLoginLink.click();
    }

    @When("user can Enter correct email address and password")
    public void enterCorrectCredentials() {
        // Enter valid email
        WebElement emailField = driver.findElement(By.cssSelector("input[data-qa='login-email']"));
        emailField.sendKeys("testuser@example.com");

        // Enter valid password
        WebElement passwordField = driver.findElement(By.cssSelector("input[data-qa='login-password']"));
        passwordField.sendKeys("TestPassword123");
    }

    @Then("user click on login button")
    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(By.cssSelector("button[data-qa='login-button']"));
        loginButton.click();

        // Verify successful login (user should be redirected)
        try {
            Thread.sleep(2000); // Wait for page load
            String currentUrl = driver.getCurrentUrl();
            Assert.assertFalse(currentUrl.contains("/login"),
                    "User should be redirected away from login page after successful login");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    @Then("user can Enter Incorrect email address and password and click on login button")
    public void enterIncorrectCredentialsAndLogin() {
        // Enter invalid email
        WebElement emailField = driver.findElement(By.cssSelector("input[data-qa='login-email']"));
        emailField.sendKeys("invalid@example.com");

        // Enter invalid password
        WebElement passwordField = driver.findElement(By.cssSelector("input[data-qa='login-password']"));
        passwordField.sendKeys("wrongpassword");

        // Click login button
        WebElement loginButton = driver.findElement(By.cssSelector("button[data-qa='login-button']"));
        loginButton.click();

        // Verify user remains on login page
        try {
            Thread.sleep(2000); // Wait for response
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("/login"),
                    "User should remain on login page for invalid credentials");

            // Check for error message (if present)
            try {
                WebElement errorMsg = driver.findElement(By.xpath("//*[contains(text(),'Your email or password is incorrect')]"));
                Assert.assertTrue(errorMsg.isDisplayed(), "Error message should be displayed");
            } catch (Exception e) {
                System.out.println("Error message not found or has different text");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

