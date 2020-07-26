package cn.beanbang.microservice.consumerfeign.controller;

import cn.beanbang.microservice.consumerfeign.service.HelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/test/hello")
    @HystrixCommand(fallbackMethod = "consumerFallback")
    // 假如在此开启了控制器的熔断，同时在 Feign 的接口也开启了 fallback，那么将返回这里的 fallbackMethod
    public String hello(String name) {
        return helloService.hello(name);
    }

    @GetMapping("/test/whoami")
    public String whoami() {
        return helloService.whoami();
    }

    public String consumerFallback(String name) {
        return "熔断了 HystrixCommand, name=" + name;
    }
}
