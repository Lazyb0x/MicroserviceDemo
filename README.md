# MicroService Demo

基于 Spring Cloud 的微服务小 Demo。

## 框架

服务发现和配置中心：[Nacos](https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html)

接口调用：[RestTemplate](https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#rest-client-access) 和 [Feign](https://cloud.spring.io/spring-cloud-static/spring-cloud-openfeign/current/reference/html/)

负载均衡：[Ribbon](https://cloud.spring.io/spring-cloud-netflix/2.2.x/reference/html/#hystrix-timeouts-and-ribbon-clients)

熔断器：[Hystrix](https://cloud.spring.io/spring-cloud-netflix/2.2.x/reference/html/#circuit-breaker-hystrix-clients)

API 网关：[Spring Cloud Gateway](https://cloud.spring.io/spring-cloud-gateway/2.2.x/reference/html/)

## 部署

需要独立安装 Nacos。参考[文档](https://nacos.io/zh-cn/docs/quick-start.html)。

安装完 Nacos 后需要导入几个服务的配置。将 nacos_config_export 下的文件打包成 zip，然后在 Nacos 的 配置管理 -> 配置列表 里面上传这个文件导入配置。

```shell
zip -r nacos_config.zip nacos_config_export/*
```

然后就可以导入项目启动了。