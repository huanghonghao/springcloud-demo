package com.hhh.ribbon.config;

import com.hhh.common.annotation.ExcludeFromComponentScan;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ExcludeFromComponentScan
public class TestConfiguration {

    /**
     * 延迟小的优先调用
     * @return
     */
/*    @Bean
    public IPing ribbonPing() {
        return new PingUrl();
    }*/

    /**
     * 随机均衡策略
     *
     * @return
     */
    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
