package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NestedFlight {

    private String number;
    private String iata;
    private String icao;
    @JsonIgnore
    private Codeshared codeshared;

    public NestedFlight() {
        super();
    }
    public NestedFlight(String number, String iata, String icao, Codeshared codeshared) {
        super();
        this.number = number;
        this.iata = iata;
        this.icao = icao;
        this.codeshared = codeshared;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getIata() {
        return iata;
    }
    public void setIata(String iata) {
        this.iata = iata;
    }
    public String getIcao() {
        return icao;
    }
    public void setIcao(String icao) {
        this.icao = icao;
    }
    public Codeshared getCodeshared() {
        return codeshared;
    }
    public void setCodeshared(Codeshared codeshared) {
        this.codeshared = codeshared;
    }
    @Override
    public String toString() {
        return "NestedFlight [number=" + number + ", iata=" + iata + ", icao=" + icao + ", codeshared=" + codeshared
                + "]";
    }

}
