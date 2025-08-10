package StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class TC06_VerifySubscriptionInHomePage {

    @Given("user open home page")
    public void HomePage () throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) Hooks.driver;
        js. executeScript("window.scrollBy(0,document.body.scrollHeight)");


        //Assertion for Subscription part in the home page
        // Assert.assertTrue(Hooks.driver.findElement(By.className("single-widget")).isDisplayed());
    }

    @When("user enter email to subscribe")
    public void Enter_Email_ () {
        // User enter valid Email
        Hooks.driver.findElement(By.id("susbscribe_email")).sendKeys("S@h.com");
    }

    @Then("user can Click subscribe button")
    public void Subscribe () {
        // user click on subscription button to verify subscribe
        Hooks.driver.findElement(By.id("subscribe")).click();
        Assert.assertTrue(Hooks.driver.findElement(By.name("You have been successfully subscribed!")).isDisplayed());
    }

    @When("user enter non Existing email")
    public void nonExistingEmail () {
        // user enter non existing email
        Hooks.driver.findElement(By.id("susbscribe_email")).sendKeys("S123");
        Hooks.driver.findElement(By.id("subscribe")).click();
    }

    @Then("user cannot verify subscription")
    public void Subscribe_Fail () {
        Hooks.driver.findElement(By.id("subscribe")).click();
        Assert.assertFalse(Hooks.driver.findElement(By.name("You have been successfully subscribed!")).isDisplayed());
    }
}
