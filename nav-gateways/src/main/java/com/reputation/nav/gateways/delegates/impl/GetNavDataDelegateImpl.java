package com.reputation.nav.gateways.delegates.impl;

import com.reputation.nav.gateways.delegates.IGetNavDataDelegate;
import com.reputation.nav.gateways.model.NavData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class GetNavDataDelegateImpl implements IGetNavDataDelegate {

    @Autowired
    private RestTemplate restTemplate;

    private static final String url = "https://api.mfapi.in/mf/{id}";

    public ResponseEntity<NavData> getNavData(int schemeCode) {


        ResponseEntity<NavData> responseEntity = restTemplate.getForEntity(url, NavData.class, schemeCode);


        return responseEntity;
    }
}
