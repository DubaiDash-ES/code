package com.example.consumerdash;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.example.consumerdash.StateRepository;

import java.util.*;
import static org.hamcrest.core.Is.is;


// @SpringBootTest
// @ContextConfiguration
// @ActiveProfiles("test")
public class StepDefinitions extends CucumberRoot {

    @Given("^a new state with Icao24 (.+) and origin country (.+) and velocity (.+)$")
    public void newState(String Icao24, String origin_country, double velocity) throws Throwable {
        stateTest = new State();
        stateTest.setIcao24("111111");
        stateTest.setOrigin_country("Portugal");
        stateTest.setVelocity(150.0);
        repository.save(stateTest);
    }

    @When("^the user checks the airplane info$")
    public void getStateInfo() throws Throwable {
        List <State> testState = (List<State>) repository.findByIcao24(stateTest.getIcao24());
        stateQuery = testState.get(0);
    }

    @Then("^the airplane Icao24, origin country and velocity should be (.+), (.+) and (.+), respectively$")
    public void assertStateInfo(String Icao24, String origin_country, double velocity) throws Throwable {
        assertEquals(Icao24, is(stateQuery.getIcao24()));
        assertEquals(origin_country, is(stateQuery.getOrigin_country()));
        assertEquals(velocity, is(stateQuery.getVelocity()));
        repository.delete(stateTest);
        repository.delete(stateQuery);
    }
}