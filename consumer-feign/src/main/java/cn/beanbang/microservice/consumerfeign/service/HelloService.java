package cn.beanbang.microservice.consumerfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-provider", fallback = HelloServiceFallback.class)
public interface HelloService {

    @RequestMapping(method = RequestMethod.GET, value = "/test/hello")
    String hello(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.GET, value = "/test/whoami")
    String whoami();
}
