package com.hhh.dataserver.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResourceController {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @GetMapping("/product/resource1")
    public String resource1() {
//        SecurityContextHolder securityContextHolder = new SecurityContextHolder();
//        System.out.println(securityContextHolder.toString());
        return null;
    }

    @GetMapping("/serviceUrl")
    public String serviceUrl() {
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("DATASERVER", false);
        return instance.getHomePageUrl();
    }

    @GetMapping("/instance-info")
    public ServiceInstance showInfo() {
        List<ServiceInstance> list = discoveryClient.getInstances("DATASERVER");
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @GetMapping("/restTest")
    public String restTest(@RequestParam("name") String name) {
        return "hi " + name + " ,i am from port: " + port;
    }

}
