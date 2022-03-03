package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class Flight {

    @DateTimeFormat
    private Date flight_date;
    private String flight_status;
    @JsonProperty("departure")
    private DepartureArrival departure;
    @JsonProperty("arrival")
    private DepartureArrival arrival;
    @JsonProperty("airline")
    private Airline airline;
    @JsonProperty("flight")
    private NestedFlight nestedFlight;
    @JsonIgnore
    private Aircraft aircraft;
    @JsonIgnore
    private Live live;

    public Flight() {
        super();
    }

    public Flight(Date flight_date, String flight_status, DepartureArrival departure, DepartureArrival arrival,
                  Airline airline, NestedFlight flight, Aircraft aircraft, Live live) {
        super();
        this.flight_date = flight_date;
        this.flight_status = flight_status;
        this.departure = departure;
        this.arrival = arrival;
        this.airline = airline;
        this.nestedFlight = flight;
        this.aircraft = aircraft;
        this.live = live;
    }


    public Date getFlight_date() {
        return flight_date;
    }

    public void setFlight_date(Date flight_date) {
        this.flight_date = flight_date;
    }

    public String getFlight_status() {
        return flight_status;
    }

    public void setFlight_status(String flight_status) {
        this.flight_status = flight_status;
    }

    public DepartureArrival getDeparture() {
        return departure;
    }

    public void setDeparture(DepartureArrival departure) {
        this.departure = departure;
    }

    public DepartureArrival getArrival() {
        return arrival;
    }

    public void setArrival(DepartureArrival arrival) {
        this.arrival = arrival;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public NestedFlight getNestedFlight() {
        return nestedFlight;
    }

    public void setNestedFlight(NestedFlight flight) {
        this.nestedFlight = flight;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Live getLive() {
        return live;
    }

    public void setLive(Live live) {
        this.live = live;
    }

    @Override
    public String toString() {
        return "Flight [flight_date=" + flight_date + ", flight_status=" + flight_status + ", departure=" + departure
                + ", arrival=" + arrival + ", airline=" + airline + ", nestedFlight=" + nestedFlight + ", aircraft="
                + aircraft +  "]";
    }

}
