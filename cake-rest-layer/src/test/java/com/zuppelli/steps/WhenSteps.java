package com.zuppelli.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.When;

import java.util.logging.Logger;

public class WhenSteps
{
    public static Logger logger = Logger.getLogger( WhenSteps.class.getName() );

    @When("^I order a '(.+)' cake$")
    public void i_order_a_kilo_cake(Integer kilos) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
