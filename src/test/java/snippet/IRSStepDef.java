package snippet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class IRSStepDef {
    private double notional;
    private double fixedInterestRate;
    private Date today;
    private Date startDate;
    private Date liborDate;
    private double fixing;

    @Given("^the notional is \"(.*?)\"€$")
    public void the_notional_is_€(double notional) {
        this.notional = notional;
    }

    @Given("^we agreed on a fixed interest rate of \"(.*?)\"%$")
    public void we_agreed_on_a_fixed_interest_rate_of(double fixedInterestRate) {
        this.fixedInterestRate = fixedInterestRate;
    }

    @Given("^today is \"(.*?)\"$")
    public void today_is(Date today) {
        this.today = today;
    }

    @Given("^the start date is \"(.*?)\"$")
    public void the_start_date_is(Date startDate) {
        this.startDate = startDate;
    }

    @Given("^the observed Libor rates at \"(.*?)\" is \"(.*?)\"%$")
    public void the_observed_Libor_rates_at_is(Date liborDate, double fixing) throws Throwable {
        this.liborDate = liborDate;
        this.fixing = fixing;
    }

    @Then("^I should receive \"(.*?)\"€$")
    public void i_should_receive_€(double amount) throws Throwable {
        assertThat(IRS.calculate(fixing), is(amount));
    }

    @Then("^I should pay \"(.*?)\"€$")
    public void i_should_pay_€(double amount) throws Throwable {
        assertThat(IRS.calculate(fixing), is(-amount));
    }
}

