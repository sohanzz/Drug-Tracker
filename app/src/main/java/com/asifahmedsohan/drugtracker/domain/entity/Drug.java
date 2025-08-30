package com.asifahmedsohan.drugtracker.domain.entity;


public class Drug {
    private String rxcui;
    private String name;

    public Drug(String rxcui, String name) {
        this.rxcui = rxcui;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRxcui() {
        return rxcui;
    }

    public void setRxcui(String rxcui) {
        this.rxcui = rxcui;
    }
}
