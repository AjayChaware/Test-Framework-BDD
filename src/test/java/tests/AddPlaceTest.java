package tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "json:target/jsonReports/cucumber_reports.json",
        features = "src/test/resources/features/AddPlace.feature",
        glue = "stepDef"
//        tags = "~@ignore"
)
public class AddPlaceTest {
    private AddPlaceTest() {
    }


}
