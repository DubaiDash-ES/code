package com.example.consumerdash;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
class ConsumerdashApplicationTests {

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
    public void requestArrival() throws Exception {
        System.out.println("Request arrival");
        this.mockMvc.perform(get("/arrival")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void requestDeparture() throws Exception {
        System.out.println("Request departure");
        this.mockMvc.perform(get("/departure")).andDo(print()).andExpect(status().isOk());
    }

    // verificar que o país que recebemos não é nulo e que não é os United Arab Emirates
    // @Test
    // public void checkMostPopularOriginCountry() throws Exception {
    //     System.out.println("Check most popular origin country");
    //     this.mockMvc.perform(get("/mostpopularorigincountry")).andDo(print()).andExpect(status().isOk());
    // }

}
