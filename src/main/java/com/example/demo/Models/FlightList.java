package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FlightList {

    @JsonProperty("data")
    private List<Flight> flightList;

    public FlightList() {}


    public FlightList(List<Flight> flightList) {
        super();
        this.flightList = flightList;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> airlineRouteList) {
        this.flightList = airlineRouteList;
    }

    public List<Flight> findAllFlights(){
        return flightList;
    }

    public void addFlight(Flight flight) {
        flightList.add(flight);
    }

    public int size() {
        return flightList.size();
    }

}
