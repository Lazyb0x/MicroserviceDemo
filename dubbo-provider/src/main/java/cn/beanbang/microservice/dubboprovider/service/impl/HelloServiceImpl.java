package cn.beanbang.microservice.dubboprovider.service.impl;

import cn.beanbang.microservice.service.IHelloService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class HelloServiceImpl implements IHelloService {


    @Override
    public String greet(String userName) {
        return "Hello, " + userName;
    }

}
