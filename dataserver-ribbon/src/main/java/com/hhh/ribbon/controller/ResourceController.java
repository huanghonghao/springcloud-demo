package com.hhh.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ResourceController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/restTest")
    public String restTest(@RequestParam(name = "name") String name) {
        // 负载均衡策略serviceId为小写dataserver, 这里也要小写，大写时定义的负载均衡策略则不起作用
        return restTemplate.getForObject("http://dataserver/restTest?name=" + name, String.class);
    }

    @GetMapping("/ribbonTest")
    public String ribbonTest() {
        /*
            选举服务, 默认为ZoneAwareLoadBalancer 线性轮询的方式
         */
        ServiceInstance serviceInstance = loadBalancerClient.choose("dataserver");
        System.out.println(serviceInstance.toString());

        ServiceInstance serviceInstance2 = loadBalancerClient.choose("dataserver2");
        System.out.println(serviceInstance2.toString());
        return "1";
    }

}
