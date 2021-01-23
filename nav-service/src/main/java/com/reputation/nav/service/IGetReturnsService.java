package com.reputation.nav.service;

import com.reputation.nav.model.ReturnTable;

import java.util.List;

public interface IGetReturnsService {

    List<ReturnTable> getReturnTable(int investmentPeriod, int horizon, int schemeCode);
}
