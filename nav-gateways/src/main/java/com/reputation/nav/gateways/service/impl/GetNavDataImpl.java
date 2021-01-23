package com.reputation.nav.gateways.service.impl;

import com.reputation.nav.gateways.delegates.IGetNavDataDelegate;
import com.reputation.nav.gateways.model.NavData;
import com.reputation.nav.gateways.service.IGetNavData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class GetNavDataImpl implements IGetNavData {

    @Autowired
    IGetNavDataDelegate getNavDataDelegateImpl;


    public NavData getNavData(int schemeCode) {

        ResponseEntity<NavData> responseEntity = getNavDataDelegateImpl.getNavData(schemeCode);


        return responseEntity.getBody();
    }
}
