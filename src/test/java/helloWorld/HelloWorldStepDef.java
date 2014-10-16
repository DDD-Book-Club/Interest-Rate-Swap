package helloWorld;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HelloWorldStepDef {
    private Baby b;

    public class Baby {

        public String speak() {
            return "Hello World";
        }

    }

    @When("^the baby comes to the world$")
    public void the_baby_comes_to_the_world() {
        b = new Baby();
    }

    @Then("^he should say \"([^\"]*)\"$")
    public void he_should_say(String message) {
        assertThat(b.speak(), is(message));
    }
}
