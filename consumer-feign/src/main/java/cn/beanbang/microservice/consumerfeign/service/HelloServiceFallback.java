package cn.beanbang.microservice.consumerfeign.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloServiceFallback implements HelloService {

    @Override
    public String hello(String name) {
        log.error("Feign 熔断了！");
        return "Feign 熔断了，参数 name=" + name;
    }

    @Override
    public String whoami() {
        log.error("Feign 熔断了！");
        return "Feign 熔断了！";
    }
}
