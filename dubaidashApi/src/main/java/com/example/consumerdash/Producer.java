package com.example.consumerdash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC1 = "arrivals";
    private static final String TOPIC2 = "departures";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessageArrival(String message) {
        logger.info(String.format("#### -> Producing arrival message -> %s", message));
        this.kafkaTemplate.send(TOPIC1, message);
    }

    public void sendMessageDeparture(String message) {
        logger.info(String.format("#### -> Producing departure message -> %s", message));
        this.kafkaTemplate.send(TOPIC2, message);
    }
}
