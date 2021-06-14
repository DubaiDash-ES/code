package com.project.dubaidash;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.project.dubaidash.controller.ProducerController;
import com.project.dubaidash.models.ParsingObject;

import org.springframework.kafka.KafkaException;
@SpringBootTest
@AutoConfigureMockMvc
class DubaidashApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ProducerController producerController;
	@Autowired
    ParsingObject parsingObject;
	

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		Assertions.assertEquals( parsingObject.parseObject(producerController.endpointAPI).getStatusCode(), HttpStatus.OK);
	}
	@Test
	void contextLoads() throws Exception{
		Assertions.assertNotNull(producerController);
	}

	@Test
	public void shouldReturnDefaultMessage() throws Exception
	{
		try
		{
			this.mockMvc.perform(get("/getstates")).andDo(print()).andExpect(status().isOk());
		}
		catch(org.springframework.web.util.NestedServletException e)
		{
			if(!(e.getCause() instanceof KafkaException))
			{
				throw new Exception(e.getCause());
			}
		}
		
	}

}
