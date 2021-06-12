package com.example.consumerdash;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ConsumerdashUseCasesTests {

    private String testMsg = "{\"time\":1623173370,\"states\":[[\"896f05\",\"OP6     \",\"United Arab Emirates\",1623173359,1623173359,55.3993,25.2333,null,true,7.72,222.19,null,null,null,null,false,0],[\"896e85\",\"OP26    \",\"United Arab Emirates\",1623173351,1623173351,55.357,25.2526,null,true,7.2,298.12,null,null,null,null,false,0],[\"896e88\",\"GO2     \",\"United Arab Emirates\",1623173068,1623173068,55.3917,25.2357,null,true,7.72,120.94,null,null,null,null,false,0],[\"89620e\",\"UAE9329 \",\"United Arab Emirates\",1623173362,1623173369,55.3789,25.239,null,true,0.77,8.44,null,null,null,null,false,0],[\"896ed4\",\"OP14    \",\"United Arab Emirates\",1623173369,1623173369,55.359,25.2641,null,true,8.75,129.38,null,null,null,null,false,0],[\"896eb6\",\"SW4     \",\"United Arab Emirates\",1623173360,1623173360,55.3498,25.2684,null,true,2.06,33.75,null,null,null,null,false,0],[\"8963d3\",\"UAE726  \",\"United Arab Emirates\",1623173369,1623173369,55.3641,25.2505,null,true,10.29,286.88,null,null,null,null,false,0],[\"8963e2\",\"UAE124  \",\"United Arab Emirates\",1623173366,1623173366,55.3501,25.2538,null,true,2.83,33.75,null,null,null,null,false,0],[\"896523\",\"UAE840  \",\"United Arab Emirates\",1623173365,1623173370,55.3636,25.2482,null,true,0,210.94,null,null,null,null,false,0],[\"896503\",\"ABY128  \",\"United Arab Emirates\",1623173314,1623173314,55.519,25.3237,null,true,0,210.94,null,null,null,null,false,0],[\"896515\",\"FDB1627 \",\"United Arab Emirates\",1623173363,1623173363,55.3514,25.2533,null,true,0,30.94,null,null,null,null,false,0],[\"89647d\",\"A6EUS   \",\"United Arab Emirates\",1623173280,1623173280,55.3813,25.2519,null,true,0,30.94,null,null,null,null,false,0],[\"8964df\",\"EFC15X  \",\"United Arab Emirates\",1623173369,1623173369,55.0947,24.8632,571.5,false,78.23,207.41,0.33,null,457.2,null,false,0],[\"76cccd\",\"SIA7373 \",\"Singapore\",1623173369,1623173369,55.5106,25.3262,null,true,0,33.75,null,null,null,null,false,0],[\"06a131\",\"QQE950  \",\"Qatar\",1623173369,1623173369,55.4754,25.2736,1874.52,false,127.93,121.52,-11.38,null,1905,null,false,0],[\"06a0a5\",\"QTR572  \",\"Qatar\",1623173369,1623173369,55.3933,25.0707,12496.8,false,253.64,78.42,-0.33,null,13296.9,null,false,0]]}";
    @Autowired
    private ConsumerdashController controller;

    @Autowired
	private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
            System.out.println("Consumerdash Controller exists");
            assertNotNull(controller);
    }

    @Test
    public void checkStates() throws Exception {
        System.out.println("Checking States");
        Consumer.fillStatesTest(testMsg);
        List<State> states = controller.data();
        List<String> icaos = new ArrayList<String>();
        for(State state : states)
        {
            if(icaos.contains(state.icao24))
            {
                throw new Exception("ERROR STATES - Same flight received at least twice.");
            }
            if(state.latitude < 24.724665 || state.latitude > 25.361084 || state.longitude < 54.874123  || state.longitude > 55.586776)
            {
                throw new Exception("ERROR STATES - Flight is out of the requested area.");
            }
            if(state.velocity < 0)
            {
                throw new Exception("ERROR STATES - Flight has a negative velocity.");
            }
            if(state.velocity == 0 && (state.on_ground == false && state.geo_altitude != 0))
            {
                throw new Exception("ERROR STATES - Flight has 0 velocity while not on the ground.");
            }
        }
    }

    @Test
    public void checkArrivals() throws Exception {
        System.out.println("Checking Arrivals");
        Consumer.fillStatesTest(testMsg);
        List<State> arrivals = controller.arrival();
        if(arrivals.size() > controller.data().size())
        {
            throw new Exception("ERROR ARRIVALS - There are more arrivals then flights.");
        }
        for(State state : arrivals)
        {
            if(state.origin_country.compareTo("United Arab Emirates") == 0)
            {
                throw new Exception("ERROR ARRIVALS - The plane arrived from United Arab Emirates.");
            }
            if(state.on_ground == false)
            {
                throw new Exception("ERROR ARRIVALS - The plane is not on the ground.");
            }
        }
    }

    @Test
    public void checkDepartures() throws Exception {
        System.out.println("Checking Departures");
        Consumer.fillStatesTest(testMsg);
        List<State> departures = controller.departure();
        if(departures.size() > controller.data().size())
        {
            throw new Exception("ERROR DEPARTURES - There are more departures then flights.");
        }
        for(State state : departures)
        {
            if(state.origin_country.compareTo("United Arab Emirates") != 0)
            {
                throw new Exception("ERROR DEPARTURES - The plane did not depart from United Arab Emirates.");
            }
            if(state.on_ground == false)
            {
                throw new Exception("ERROR DEPARTURES - The plane is not on the ground.");
            }
        }
    }


    // verificar que o país que recebemos não é nulo e que não é os United Arab Emirates
    // @Test
    // public void checkMostPopularOriginCountry() throws Exception {
    //     System.out.println("Check most popular origin country");
    //     this.mockMvc.perform(get("/mostpopularorigincountry")).andDo(print()).andExpect(status().isOk());
    // }

}
