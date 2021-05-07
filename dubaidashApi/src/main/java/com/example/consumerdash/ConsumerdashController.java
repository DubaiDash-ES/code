package com.example.consumerdash;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ConsumerdashController {

    @RequestMapping("/")
    public String index() {
        return "Hi! Welcome to DubaiDash ✈️ RestApi we are still building it  \uD83C\uDFD7️ ";
    }
}
