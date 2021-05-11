package com.project.dubaidash.controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.project.dubaidash.models.ParsingObject;
import com.project.dubaidash.models.Producer;
import com.project.dubaidash.models.State;
import com.project.dubaidash.models.StateInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class ProducerController {
    
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Producer prod;
    // Parsing object class. Used to retrieve data from API.
    @Autowired
    ParsingObject parsingObject;    

    // ObjectMapper object. Used to assign API info to a class.
    private ObjectMapper mapper = new ObjectMapper();  

    // API Endpoint URL - All states flying inside a square on Dubai
    String endpointAPI = "https://opensky-network.org/api/states/all?lamin=24.724665&lomin=54.874123&lamax=25.361084&lomax=55.586776";
    
    List<State> states;
    String uelele = "";

    // Index page
    @GetMapping("/")
    public String states(Model model) throws IOException
    {
        prod.sendMessage("Index sending...");
        return "index";
    }

    // Retrieve states from API 
    @GetMapping("/states")
    @ResponseBody
    public List<State> getStates()
    {
        // ResponseEntity<Object> response = parsingObject.parseObject(endpointAPI);
        // Object objects = response.getBody();

        StateInfo state_info = mapper.convertValue(uelele, StateInfo.class);
        state_info.Fill_States();        

        this.states = state_info.getStateObj();
        return this.states;
    }

    // Retrieve states from API 
    @GetMapping("/getstates")
    @ResponseBody
    @Scheduled(fixedRate = 5000)
    public void getStatesList()
    {
        ResponseEntity<Object> response = parsingObject.parseObject(endpointAPI);
        Gson gson = new Gson();
        String json = gson.toJson(response.getBody());
        prod.sendMessage(json);
    }
}
