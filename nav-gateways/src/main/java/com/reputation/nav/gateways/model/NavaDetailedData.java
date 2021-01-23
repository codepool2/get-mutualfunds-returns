package com.reputation.nav.gateways.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class NavaDetailedData {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    private Float nav;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getNav() {
        return nav;
    }

    public void setNav(Float nav) {
        this.nav = nav;
    }
}
