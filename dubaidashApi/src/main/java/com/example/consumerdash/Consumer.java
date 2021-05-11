package com.example.consumerdash;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
    List<State> states;


    @KafkaListener(topics = "states", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        StateInfo estados = new ObjectMapper().convertValue(message,StateInfo.class);
        estados.Fill_States();
        this.states = estados.getStateObj();
        logger.info(states.toString());
    }
}
