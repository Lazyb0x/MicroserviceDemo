package cn.beanbang.seataproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SeataProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataProductServiceApplication.class, args);
    }

}
