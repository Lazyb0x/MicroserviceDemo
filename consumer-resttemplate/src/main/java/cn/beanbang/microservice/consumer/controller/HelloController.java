package cn.beanbang.microservice.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class HelloController {

    @Autowired
    RestTemplate restTemplate;

    private static final String REST_URL_PREFIX = "http://service-provider";

    @GetMapping("/hello")
    public String hello(String name) {
        return restTemplate.getForObject(
                REST_URL_PREFIX + "/test/hello?name={name}", String.class, name);
    }

    @GetMapping("/whoami")
    public String whoami() {
        return restTemplate.getForObject(
                REST_URL_PREFIX + "/test/whoami", String.class);
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
