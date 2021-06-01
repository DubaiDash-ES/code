package com.example.consumerdash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ConsumerdashController {

    @Autowired
    private StateRepository repository;

    @RequestMapping("/")
    public String index() {
        return "Hi! Welcome to DubaiDash ✈️ RestApi we are still building it  \uD83C\uDFD7️ ";
    }

    @CrossOrigin(origins = "http://192.168.160.87:20005")
    @RequestMapping("/data")
    public List<State> data(){
        return Consumer.states;
    }

    @CrossOrigin(origins = "http://192.168.160.87:20005")
    @RequestMapping("/alldbdata")
    public List<State> alldbdata(){

        return repository.findAll();
    }

    @CrossOrigin(origins = "http://192.168.160.87:20005")
    @RequestMapping("/mostpopularorigincountry")
    public String mostpopularorigincountry(){
        String country = "Default";
        int max_ocurrences = 0;
        List<String> seen_countries = new ArrayList<String>();
        for(State state : repository.findAll())
        {
            if(!seen_countries.contains(state.origin_country) && state.origin_country.compareTo("United Arab Emirates") != 0)
            {
                seen_countries.add(state.origin_country);
                int countries_found = repository.findByorigin_country(state.origin_country).size();
                if(countries_found > max_ocurrences)
                {
                    country = state.origin_country;
                    max_ocurrences = countries_found;
                }
            }
        }

        return country;

    }

    @CrossOrigin(origins = "http://192.168.160.87:20005")
    @RequestMapping("/arrival")
    public List<State> arrival(){
        return Consumer.arrivals;
    }

    @CrossOrigin(origins = "http://192.168.160.87:20005")
    @RequestMapping("/departure")
    public List<State> departure(){
        return Consumer.departures;
    }
}
