package com.project.dubaidash.controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dubaidash.models.ParsingObject;
import com.project.dubaidash.models.State;
import com.project.dubaidash.models.StateInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProducerController {
    
    // Parsing object class. Used to retrieve data from API.
    @Autowired
    ParsingObject parsingObject;    

    // ObjectMapper object. Used to assign API info to a class.
    private ObjectMapper mapper = new ObjectMapper();   

    // API Endpoint URL - All states flying inside a square on Dubai
    String endpointAPI = "https://opensky-network.org/api/states/all?lamin=24.724665&lomin=54.874123&lamax=25.361084&lomax=55.586776";
    
    List<State> states;

    // Index page
    @GetMapping("/")
    public String states(Model model) throws IOException
    {
        return "index";
    }

    // Retrieve states from API 
    @GetMapping("/states")
    public void getStates()
    {
        ResponseEntity<Object> response = parsingObject.parseObject(endpointAPI);
        Object objects = response.getBody();

        StateInfo state_info = mapper.convertValue(objects, StateInfo.class);
        state_info.Fill_States();        

        this.states = state_info.getStateObj();
    }

}
