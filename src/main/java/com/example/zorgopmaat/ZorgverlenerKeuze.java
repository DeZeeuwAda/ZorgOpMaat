package com.example.zorgopmaat;

public class ZorgverlenerKeuze {

    private int zorgverlenerId;
    private String naam;

    public ZorgverlenerKeuze(int zorgverlenerId, String naam) {
        this.zorgverlenerId = zorgverlenerId;
        this.naam = naam;
    }

    public int getZorgverlenerId() {
        return zorgverlenerId;
    }

    @Override
    public String toString() {
        return naam;
    }
}
