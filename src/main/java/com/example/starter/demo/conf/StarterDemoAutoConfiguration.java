package com.example.starter.demo.conf;

import com.example.starter.demo.service.StartDemoService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author dd
 * @date 2019-05-27
 */
@Configuration
@ConditionalOnClass(StartDemoService.class)
@EnableConfigurationProperties(DemoProperties.class)
public class StarterDemoAutoConfiguration {

    @Resource
    private DemoProperties demoProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "starter.demo.hello", value = "enabled", havingValue = "true")
    StartDemoService startDemoService (){
        return new StartDemoService(demoProperties.getEnabled(),demoProperties.getName(), demoProperties.getAge(), demoProperties.getHometown());
    }

}
