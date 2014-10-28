package bdd.approach;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		strict = true, 
		features={"classpath:bdd.approach"},
		tags={"~@ignore"}
)
public class RunCucumber {

}
