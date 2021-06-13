package com.example.consumerdash;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
// @CucumberContextConfiguration
// @SpringBootTest
@CucumberOptions(features = {"src/test/resources"})
class CucumberIntegrationTest {

}