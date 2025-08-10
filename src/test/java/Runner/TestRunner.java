package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:\\Users\\5570\\IdeaProjects\\Diploma_Graduation_Project\\src\\main\\resources\\Features",
        glue = {"stepDefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/orangehrm-report.html",
                "json:target/cucumber-reports/orangehrm-report.json",
                "junit:target/cucumber-reports/orangehrm-report.xml"
        },
        monochrome = true,
        dryRun = false,
        tags = "@smoke or @regression")

    // Empty class - Cucumber handles the execution
public class TestRunner {

}
