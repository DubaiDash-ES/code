package com.example.consumerdash;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.utils.KafkaTestUtils;
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
        repository.deleteAll();
        stateTest = new State();
        stateTest.setIcao24(Icao24);
        stateTest.setOrigin_country(origin_country);
        stateTest.setVelocity(velocity);
        repository.save(stateTest);
    }

    @When("^the user checks the airplane info$")
    public void getStateInfo() throws Throwable {
        List <State> testState = (List<State>) repository.findByIcao24(stateTest.getIcao24());
        stateQuery = testState.get(0);
    }

    @Then("^the airplane Icao24, origin country and velocity should be (.+), (.+) and (.+), respectively$")
    public void assertStateInfo(String Icao24, String origin_country, double velocity) throws Throwable {
        try
        {
            assertEquals(Icao24, stateQuery.getIcao24());
            assertEquals(origin_country, stateQuery.getOrigin_country());
            assertEquals(velocity, stateQuery.getVelocity());
            repository.delete(stateTest);
            repository.delete(stateQuery);
        }
        catch(Exception e)
        {
            repository.delete(stateTest);
            repository.delete(stateQuery);
            throw new Exception(e);
        }
        
    }


    @Given("^a new state with Icao24 (.+) and origin country (.+) and a value representing if plane is on the ground (.+)$")
    public void newArrivalEvent(String Icao24, String origin_country, boolean on_ground) throws Throwable {
        repository.deleteAll();
        stateTest = new State();
        stateTest.setIcao24(Icao24);
        stateTest.setOrigin_country(origin_country);
        stateTest.setOn_ground(on_ground);
        repository.save(stateTest);
    }

    @When("^the consumer will send an event if it is an arrival$")
    public void getArrivalInfo() throws Throwable {
        List <State> testState = (List<State>) repository.findByIcao24(stateTest.getIcao24());
        stateQuery = testState.get(0);
        if(!stateQuery.getOrigin_country().equals("United Arab Emirates"))
        {
            producer = configureProducer();
            producer.send(new ProducerRecord<>("arrival", 123, stateQuery.getIcao24()));
            
        }
    }

    @Then("^the airplane Icao24, origin country and on ground with (.+), (.+) and (.+), respectively, should be an arrival$")
    public void assertArrival(String Icao24, String origin_country, boolean on_ground) throws Throwable {
        try
        {

            consumer = configureConsumer("arrival");
            ConsumerRecord<Integer, String> singleRecord = KafkaTestUtils.getSingleRecord(consumer, "arrival");
            assertNotNull(singleRecord);
            assertEquals(Icao24, singleRecord.value());
            repository.delete(stateTest);
            repository.delete(stateQuery);
        }
        catch(Exception e)
        {
            repository.delete(stateTest);
            repository.delete(stateQuery);
            throw new Exception(e);
        }
        
    }


    @Given("^states with Icao24 and origin country$")
    public void newStates() throws Throwable {
        repository.deleteAll();
        stateTest1 = new State();
        stateTest2 = new State();
        stateTest3 = new State();
        stateTest1.setIcao24("111111");
        stateTest1.setOrigin_country("Portugal");
        stateTest2.setIcao24("222222");
        stateTest2.setOrigin_country("Portugal");
        stateTest3.setIcao24("333333");
        stateTest3.setOrigin_country("Spain");
        repository.save(stateTest1);
        repository.save(stateTest2);
        repository.save(stateTest3);
    }

    @When("^the user opens the web ui$")
    public void getPopularCountry() throws Throwable {
        List <State> testState = (List<State>) repository.findAll();
        int max_ocurrences = 0;
        List<String> seen_countries = new ArrayList<String>();
        for(State state : testState)
        {
            if(!seen_countries.contains(state.origin_country) && state.origin_country.compareTo("United Arab Emirates") != 0)
            {
                seen_countries.add(state.origin_country);
                int countries_found = repository.findByorigin_country(state.origin_country).size();
                if(countries_found > max_ocurrences)
                {
                    popularCountry = state.origin_country;
                    max_ocurrences = countries_found;
                }
            }
        }
    }

    @Then("^he can visualize the most popular country$")
    public void assertPopularCountry() throws Throwable {
        try
        {
            assertNotNull(popularCountry);
            assertEquals(popularCountry, "Portugal");
            repository.delete(stateTest1);
            repository.delete(stateTest2);
            repository.delete(stateTest3);
        }
        catch(Exception e)
        {
            repository.delete(stateTest1);
            repository.delete(stateTest2);
            repository.delete(stateTest3);
            throw new Exception(e);
        }
        
    }
}