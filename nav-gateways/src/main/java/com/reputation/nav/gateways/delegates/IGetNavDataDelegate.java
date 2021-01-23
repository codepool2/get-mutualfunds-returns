package com.reputation.nav.gateways.delegates;

import com.reputation.nav.gateways.model.NavData;
import org.springframework.http.ResponseEntity;

public interface IGetNavDataDelegate {

    ResponseEntity<NavData> getNavData(int schemeCode);
}
