package cn.beanbang.microservice.consumer.controller;

import cn.beanbang.microservice.service.IHelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @DubboReference
    IHelloService helloService;

    @GetMapping("/greet")
    public String greet(String name) {
        return helloService.greet(name);
    }

}
