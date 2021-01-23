package com.reputation.nav.config;


import com.reputation.nav.service.IGetReturnsService;
import com.reputation.nav.service.impl.GetReturnServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public IGetReturnsService getReturnsServiceImpl(){
        return new GetReturnServiceImpl();
    }
}
