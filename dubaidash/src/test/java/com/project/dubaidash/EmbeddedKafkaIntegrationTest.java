package com.project.dubaidash;

import static org.mockito.ArgumentMatchers.contains;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = {"embedded-test-topic"})
@SpringBootTest
public class EmbeddedKafkaIntegrationTest {

    private static String TEST_TOPIC = "embedded-test-topic";
    private String testMsg = "{\"time\":1623173370,\"states\":[[\"896f05\",\"OP6     \",\"United Arab Emirates\",1623173359,1623173359,55.3993,25.2333,null,true,7.72,222.19,null,null,null,null,false,0],[\"896e85\",\"OP26    \",\"United Arab Emirates\",1623173351,1623173351,55.357,25.2526,null,true,7.2,298.12,null,null,null,null,false,0],[\"896e88\",\"GO2     \",\"United Arab Emirates\",1623173068,1623173068,55.3917,25.2357,null,true,7.72,120.94,null,null,null,null,false,0],[\"89620e\",\"UAE9329 \",\"United Arab Emirates\",1623173362,1623173369,55.3789,25.239,null,true,0.77,8.44,null,null,null,null,false,0],[\"896ed4\",\"OP14    \",\"United Arab Emirates\",1623173369,1623173369,55.359,25.2641,null,true,8.75,129.38,null,null,null,null,false,0],[\"896eb6\",\"SW4     \",\"United Arab Emirates\",1623173360,1623173360,55.3498,25.2684,null,true,2.06,33.75,null,null,null,null,false,0],[\"8963d3\",\"UAE726  \",\"United Arab Emirates\",1623173369,1623173369,55.3641,25.2505,null,true,10.29,286.88,null,null,null,null,false,0],[\"8963e2\",\"UAE124  \",\"United Arab Emirates\",1623173366,1623173366,55.3501,25.2538,null,true,2.83,33.75,null,null,null,null,false,0],[\"896523\",\"UAE840  \",\"United Arab Emirates\",1623173365,1623173370,55.3636,25.2482,null,true,0,210.94,null,null,null,null,false,0],[\"896503\",\"ABY128  \",\"United Arab Emirates\",1623173314,1623173314,55.519,25.3237,null,true,0,210.94,null,null,null,null,false,0],[\"896515\",\"FDB1627 \",\"United Arab Emirates\",1623173363,1623173363,55.3514,25.2533,null,true,0,30.94,null,null,null,null,false,0],[\"89647d\",\"A6EUS   \",\"United Arab Emirates\",1623173280,1623173280,55.3813,25.2519,null,true,0,30.94,null,null,null,null,false,0],[\"8964df\",\"EFC15X  \",\"United Arab Emirates\",1623173369,1623173369,55.0947,24.8632,571.5,false,78.23,207.41,0.33,null,457.2,null,false,0],[\"76cccd\",\"SIA7373 \",\"Singapore\",1623173369,1623173369,55.5106,25.3262,null,true,0,33.75,null,null,null,null,false,0],[\"06a131\",\"QQE950  \",\"Qatar\",1623173369,1623173369,55.4754,25.2736,1874.52,false,127.93,121.52,-11.38,null,1905,null,false,0],[\"06a0a5\",\"QTR572  \",\"Qatar\",1623173369,1623173369,55.3933,25.0707,12496.8,false,253.64,78.42,-0.33,null,13296.9,null,false,0]]}";

    @Autowired
    EmbeddedKafkaBroker embeddedKafkaBroker;

    @Test
    public void testReceivingKafkaEvents() {
        Consumer<Integer, String> consumer = configureConsumer();
        Producer<Integer, String> producer = configureProducer();

        producer.send(new ProducerRecord<>(TEST_TOPIC, 123, testMsg));

        ConsumerRecord<Integer, String> singleRecord = KafkaTestUtils.getSingleRecord(consumer, TEST_TOPIC);
        Assertions.assertNotNull(singleRecord);
        Assertions.assertEquals(singleRecord.key(), 123);
        Assertions.assertTrue(singleRecord.value().contains("states"));
        consumer.close();
        producer.close();
    }

    private Consumer<Integer, String> configureConsumer() {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("baeldung", "true", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        Consumer<Integer, String> consumer = new DefaultKafkaConsumerFactory<Integer, String>(consumerProps)
                .createConsumer();
        consumer.subscribe(Collections.singleton(TEST_TOPIC));
        return consumer;
    }

    private Producer<Integer, String> configureProducer() {
        Map<String, Object> producerProps = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
        return new DefaultKafkaProducerFactory<Integer, String>(producerProps).createProducer();
    }
}