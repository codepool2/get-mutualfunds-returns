package com.reputation.nav.gateways.model;

import java.util.ArrayList;
import java.util.List;

public class NavData {


    private List<NavaDetailedData> data = new ArrayList<NavaDetailedData>();

    public List<NavaDetailedData> getData() {
        return data;
    }

    public void setData(List<NavaDetailedData> data) {
        this.data = data;
    }
}
