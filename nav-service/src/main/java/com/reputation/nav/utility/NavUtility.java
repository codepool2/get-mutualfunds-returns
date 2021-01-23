package com.reputation.nav.utility;

import com.reputation.nav.model.Calculation;
import com.reputation.nav.exception.DateOutOfLaunchDateException;
import com.reputation.nav.model.ReturnTable;

import java.time.LocalDate;
import java.util.Map;

public final class NavUtility {

    private static final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov","Dec"};

    private NavUtility() {

    }

    public static String getReturnsPercentage(int investmentPeriod, Map<String, Float> dataMap, LocalDate endNavDate, LocalDate launchDate) {
        LocalDate startNavDate = endNavDate.minusYears(investmentPeriod);

        Float endNavData = null;
        Float startNavData = null;

        try {

            endNavData = getNavData(dataMap, endNavDate, launchDate);
            startNavData = getNavData(dataMap, startNavDate, launchDate);
        } catch (DateOutOfLaunchDateException e) {

            return "0";
        }

        return calculatePercentage(investmentPeriod, endNavData, startNavData);
    }

    private static String calculatePercentage(int investmentPeriod, Float endNavData, Float startNavData) {

        double returnsPercenatge = Math.round(Math.ceil((Math.pow(endNavData / startNavData, (1f / Float.valueOf(investmentPeriod))) - 1) * 100));

        return returnsPercenatge + "%";
    }

    private static Float getNavData(Map<String, Float> dataMap, LocalDate navDate, LocalDate launchDate) {

        Float navData;

        int num_of_days = 0;

        boolean getLatestAvailableData = false;

        if (navDate.isBefore(launchDate)) {

            throw new DateOutOfLaunchDateException();
        }
        do {
            if (navDate.plusDays(num_of_days).isEqual(LocalDate.now())) {
                getLatestAvailableData = true;
            }

            if (getLatestAvailableData) {
                navData = dataMap.get(navDate.minusDays(Math.abs(num_of_days)).toString());
                num_of_days--;
            } else {
                navData = dataMap.get(navDate.plusDays(num_of_days).toString());
                num_of_days++;
            }
        } while (navData == null);

        return navData;
    }

    public static ReturnTable generateReturnTable(LocalDate endNavDate, int investmentPeriod, String percentage) {
        LocalDate startNavDate = endNavDate.minusYears(investmentPeriod);

        String month = months[endNavDate.getMonthValue() - 1] + "-" + String.valueOf(endNavDate.getYear()).substring(2);

        Calculation calculation = new Calculation();
        calculation.setEndNav(getFormattedDate(endNavDate));
        calculation.setStartNav(getFormattedDate(startNavDate));

        ReturnTable returnTable = new ReturnTable();
        returnTable.setCalculation(calculation);
        returnTable.setMonth(month);
        returnTable.setReturns(percentage);

        return returnTable;
    }

    public static String getFormattedDate(LocalDate navDate) {

        return navDate.getDayOfMonth() + "-" + months[navDate.getMonthValue() - 1] + "-" + String.valueOf(navDate.getYear()).substring(2);
    }
}
