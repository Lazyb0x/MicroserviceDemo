package cn.beanbang.microservice.gateway.config;

import cn.beanbang.microservice.gateway.filter.CustomGlobalFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersConfig {

    @Bean
    public GlobalFilter customFilter(){
        return new CustomGlobalFilter();
    }
}
