package com.reputation.nav.service.impl;

import com.reputation.nav.gateways.model.NavData;
import com.reputation.nav.gateways.model.NavaDetailedData;
import com.reputation.nav.gateways.service.IGetNavData;
import com.reputation.nav.model.ReturnTable;
import com.reputation.nav.service.IGetReturnsService;
import com.reputation.nav.utility.NavUtility;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetReturnServiceImpl implements IGetReturnsService {

    @Autowired
    private IGetNavData getNavDataImpl;

    public List<ReturnTable> getReturnTable(int investmentPeriod, int horizon, int schemeCode) {

        NavData navData = getNavDataImpl.getNavData(schemeCode);
        final LocalDate[] launchDate = {LocalDate.now()};
        Map<String, Float> indexedNavDataAgainstDate = indexNavData(navData, launchDate);

        List<ReturnTable> returnTableList = new ArrayList<>();
        LocalDate startOfInvestment = LocalDate.now().minusDays(1).minusYears(horizon);
        if(indexedNavDataAgainstDate.isEmpty()){
            return returnTableList;
        }
        for (LocalDate endDate = startOfInvestment; (endDate.isBefore(LocalDate.now().minusDays(1)) || endDate.isEqual(LocalDate.now().minusDays(1))); endDate = endDate.plusMonths(1)) {

            String percentage = NavUtility.getReturnsPercentage(investmentPeriod, indexedNavDataAgainstDate, endDate, launchDate[0]);
            returnTableList.add(NavUtility.generateReturnTable(endDate, investmentPeriod, percentage));
        }
        return returnTableList;
    }

    private Map<String, Float> indexNavData(NavData navData, final LocalDate[] launchDate) {

        Map<String, Float> map = navData.getData().stream().map(d -> {
            launchDate[0] = d.getDate().isBefore(launchDate[0]) ? d.getDate() : launchDate[0];

            return d;
        }).collect(Collectors.toMap(b -> String.valueOf(b.getDate()), NavaDetailedData::getNav, (o, n) -> o));

        return map;
    }


}
