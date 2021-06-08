package com.example.consumerdash;

import java.util.*;
import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ConsumerdashApplicationTests {

    @Autowired
    private ConsumerdashController controller;

    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private StateRepository repository;

    @Test
    void contextLoads() throws Exception {
        System.out.println("Consumerdash Controller exists");
        assertNotNull(controller);
    }

    @Test
    public void requestIndex() throws Exception {
        System.out.println("Request index");
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Hi! Welcome to DubaiDash ✈️ RestApi we are still building it  \uD83C\uDFD7️ ")));
    }

    @Test
    public void requestData() throws Exception {
        System.out.println("Request data");
        this.mockMvc.perform(get("/data")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void requestAllDBData() throws Exception {
        System.out.println("Request all db data");
        this.mockMvc.perform(get("/alldbdata")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void requestArrival() throws Exception {
        System.out.println("Request arrival");
        this.mockMvc.perform(get("/arrival")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void requestDeparture() throws Exception {
        System.out.println("Request departure");
        this.mockMvc.perform(get("/departure")).andDo(print()).andExpect(status().isOk());
    }


    @Test
    public void checkMostPopularOriginCountry() throws Exception {
        System.out.println("Check most popular origin country");
        this.mockMvc.perform(get("/mostpopularorigincountry")).andDo(print()).andExpect(content().string(containsString("Default")));
    }

    @Test
    public void notEmptyRepository() throws Exception {
        List <State> allbddata = (List <State>) repository.findAll();
        assertNotNull(allbddata);
    }

    @Test
    public void getState() throws Exception {
        State state = new State();
        state.setIcao24("12345");

        List<State> allStates = singletonList(state);

        given(repository.findAll()).willReturn(allStates);


        this.mockMvc.perform(get("/alldbdata")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].icao24", is(state.getIcao24())));

    }

    @Test
    public void getStateByPCountry() throws Exception {
        State state = new State();
        state.setOrigin_country("Default");

        List<State> allStates = singletonList(state);

        given(repository.findByorigin_country(state.getOrigin_country())).willReturn(allStates);

        this.mockMvc.perform(get("/mostpopularorigincountry")
            .contentType(APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", is(state.getOrigin_country())));
    }


}
