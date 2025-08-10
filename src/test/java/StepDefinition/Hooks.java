package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

    public class Hooks {

        public static WebDriver driver;

        @Before
        public void setUp() {

            // https://automationexercise.com/

            System.out.println("Setting up WebDriver for OrangeHRM tests...");


            // Chrome options for better performance
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-web-security");
            options.addArguments("--disable-features=VizDisplayCompositor");
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);

            // Configure driver timeouts
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

            // Maximize a window
            driver.manage().window().maximize();

            System.out.println("WebDriver setup completed successfully");
        }

        @After
        public void tearDown(Scenario scenario) {
            System.out.println("Tearing down WebDriver...");

            // Take a screenshot if a scenario fails
            if (scenario.isFailed() && driver != null) {
                try {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Screenshot");
                    System.out.println("Screenshot attached for failed scenario: " + scenario.getName());
                } catch (Exception e) {
                    System.err.println("Could not take screenshot: " + e.getMessage());
                }
            }

            // Print scenario status
            System.out.println("Scenario '" + scenario.getName() + "' status: " + scenario.getStatus());

            // Close browser
            if (driver != null) {
                driver.quit();
                driver = null;
            }

            System.out.println("WebDriver teardown completed");
        }


        // Method to switch browser if needed
        public static void setupBrowser(String ignoredBrowserName) {
            if (driver != null) {
                driver.quit();
            }


            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }

    }
