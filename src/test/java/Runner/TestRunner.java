package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        plugin = {
                "pretty",
                "json:target/jsonReport/cucumber.json",
                "de.monochromata.cucumber.report.PrettyReports:target/htmlReport"
        },
        glue = {"StepDefinitions"},
        monochrome = true,
        tags = ""
)
public class TestRunner {

}
