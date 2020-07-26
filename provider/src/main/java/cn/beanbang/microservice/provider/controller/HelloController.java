package cn.beanbang.microservice.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloController {

    @Value("provider-8001")
    String instanceId;

    @GetMapping("/hello")
    public String hello(String name){
        return String.format("Hello, %s!", name);
    }

    @GetMapping("/whoami")
    public String whoami(){
        return instanceId;
    }
}
