package com.project.dubaidash.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ParsingObject {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<Object[]> parseObjects(String url)
    {
        return restTemplate.getForEntity(url, Object[].class);
    }

    public ResponseEntity<Object> parseObject(String url)
    {
        return restTemplate.getForEntity(url, Object.class);
    }
}
