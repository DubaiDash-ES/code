package com.example.consumerdash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
public class ConsumerdashController {



    @RequestMapping("/")
    public String index() {
        return "Hi! Welcome to DubaiDash ✈️ RestApi we are still building it  \uD83C\uDFD7️ ";
    }

    @RequestMapping("/data")
    public List<State> data(){
        return Consumer.states;
    }
}
