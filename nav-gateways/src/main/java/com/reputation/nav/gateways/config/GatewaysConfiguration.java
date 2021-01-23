package com.reputation.nav.gateways.config;

import com.reputation.nav.gateways.delegates.IGetNavDataDelegate;
import com.reputation.nav.gateways.delegates.impl.GetNavDataDelegateImpl;
import com.reputation.nav.gateways.exception.RestTemplateResponseErrorHandler;
import com.reputation.nav.gateways.service.IGetNavData;
import com.reputation.nav.gateways.service.impl.GetNavDataImpl;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GatewaysConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder){

        return restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
    }

    @Bean
    public IGetNavDataDelegate getNavDataDelegateImpl(){
        return  new GetNavDataDelegateImpl();
    }

    @Bean
    public IGetNavData getNavDataImpl(){
        return new GetNavDataImpl();
    }
}
