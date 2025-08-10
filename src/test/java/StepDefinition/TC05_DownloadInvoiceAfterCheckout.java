
    package StepDefinition;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

    public class TC05_DownloadInvoiceAfterCheckout {

        SoftAssert softAssert = new SoftAssert();

        @Given("user open cart details")
        public void Cart_button() {
            String actualResult =Hooks.driver.getCurrentUrl();
            String expectedResult="https://automationexercise.com/";
            softAssert.assertEquals(actualResult,expectedResult,"home page is visible successfully");

            WebElement Cart = Hooks.driver.findElement(By.partialLinkText("Cart"));
            Cart.click();

            String actualResult1 = Hooks.driver.getCurrentUrl();
            String expectedResult1 = "https://automationexercise.com/view_cart";
            softAssert.assertEquals(actualResult1,expectedResult1,"Cart details page is disabled");

            softAssert.assertAll();
        }

        @And("user click on Proceed To Checkout button")
        public void Proceed_To_Checkout_button () {
            WebElement Checkout_button = Hooks.driver.findElement(By.linkText("Proceed To Checkout"));
            Checkout_button.click();

            Assert.assertTrue(Hooks.driver.findElement(By.className("page-subheading")).isDisplayed());

        }

        @And("user enter Comment to the order and click on Place order")
        public void Add_Comment() {
            WebElement Comment = Hooks.driver.findElement(By.className("form-control"));
            Comment.sendKeys("Please deliver the order at the time from 5 to 10 pm");

            WebElement Place_order = Hooks.driver.findElement(By.linkText("Place Order"));
            Place_order.click();
        }

        @And("user can Enter payment details")
        public void Payment () {
            WebElement NameOnCard = Hooks.driver.findElement(By.className("form-control"));
            NameOnCard.sendKeys("Sara Ahmed");

            WebElement card_number = Hooks.driver.findElement(By.className("card_number"));
            card_number.sendKeys("1111000011110000");

            WebElement cvc = Hooks.driver.findElement(By.name("cvc"));
            cvc.sendKeys("258");

            WebElement expiry_month = Hooks.driver.findElement(By.name("expiry_month"));
            expiry_month.sendKeys("09");

            WebElement expiry_year = Hooks.driver.findElement(By.name("expiry_year"));
            expiry_year.sendKeys("2030");
        }


        @And("user can click on pay and Place order Button")
        public void Payment_details() {
            WebElement Pay_Confirm_button = Hooks.driver.findElement(By.id("submit"));
            Pay_Confirm_button.click();

            Assert.assertTrue(Hooks.driver.findElement(By.name("Order Placed!")).isDisplayed());
        }

        @And("user can download the Invoice")
        public void Download_Invoice() {
            WebElement DownloadInvoice = Hooks.driver.findElement(By.linkText("Download Invoice"));
            DownloadInvoice.click();

            WebElement ContinueProcess = Hooks.driver.findElement(By.linkText("Continue"));
            ContinueProcess.click();

        }
    }

