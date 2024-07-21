package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber", glue="Selenium_Java.StepDefinition",
monochrome=true,plugin={"html:target/cucumber.html"})
public class TestNgRunner extends AbstractTestNGCucumberTests {
	
}
