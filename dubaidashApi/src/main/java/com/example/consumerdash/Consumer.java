package com.example.consumerdash;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Consumer {
    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
    static List<State> states;
    static List<State> arrivals;
    static List<State> departures;

    @Autowired
    private StateRepository repository;
    @Autowired
    private Producer prod;


    @KafkaListener(topics = "states", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        Gson ola = new Gson();
        StateInfo estados = ola.fromJson(message,StateInfo.class);
        estados.Fill_States();
        this.states = estados.getStateObj();
        List<State> chegadas = new ArrayList<>();
        List<State> partidas = new ArrayList<>();
        logger.info(estados.getStateObj().get(0).origin_country);
        for(State state : estados.getStateObj())
        {
            if(repository.findByIcao24(state.icao24).size() == 0)
            {
                repository.save(state);
            }
            if(state.on_ground) {
                if(state.origin_country.equals("United Arab Emirates"))
                    partidas.add(state);
                else
                    chegadas.add(state);
            }
        }

        prod.sendMessageDeparture(ola.toJson(partidas));
        prod.sendMessageArrival(ola.toJson(chegadas));
    }

    @KafkaListener(topics = "arrivals", groupId = "group_id")
    public void consume2(String message) throws IOException {
        logger.info(String.format("#### -> Consumed arrival message -> %s", message));
        Gson hello = new Gson();
        this.arrivals = hello.fromJson(message,new TypeToken<List<State>>(){}.getType());
    }

    @KafkaListener(topics = "departures", groupId = "group_id")
    public void consume3(String message) throws IOException {
        logger.info(String.format("#### -> Consumed departure message -> %s", message));
        Gson hello = new Gson();
        this.departures = hello.fromJson(message,new TypeToken<List<State>>(){}.getType());
    }

    public static void fillStatesTest(String message)
    {
        Gson ola = new Gson();
        StateInfo estados = ola.fromJson(message,StateInfo.class);
        estados.Fill_States();
        states = estados.getStateObj();
        List<State> chegadas = new ArrayList<>();
        List<State> partidas = new ArrayList<>();
        for(State state : estados.getStateObj())
        {
            if(state.on_ground) {
                if(state.origin_country.equals("United Arab Emirates"))
                {
                    partidas.add(state);
                }
                else
                    chegadas.add(state);
            }
        }
        arrivals = chegadas;
        departures = partidas;
    }
}
