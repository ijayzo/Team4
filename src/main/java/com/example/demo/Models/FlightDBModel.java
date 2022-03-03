package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="flights_from_api")
public class FlightDBModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int flight_id;                      //auto generated serial
    private String dep_carriercode;             //mapped to 2 letter flight.iata airline code in API response
    private String dep_fltnumber;               //mapped to flight.number in API response
    @DateTimeFormat(pattern = "yyyy-Mm-dd")
    @Temporal(TemporalType.DATE)
    private Date dep_fltdate;                   //mapped to string component of departure.scheduled in API response  Conversion to Date will be performed
    private String dep_flttime;                 //mapped to string component of departure.scheduled in API response  Format: HH:MM
    private String dep_airport;                 //mapped to 3 letter departure.iata  in API response

    @DateTimeFormat(pattern = "yyyy-Mm-dd")
    @Temporal(TemporalType.DATE)
    private Date arrivalFlightDate;             //mapped to string component of arrival.scheduled in API response   Conversion to Date will be performed
    private String arrivalTime;                 //mapped to string component of arrival.scheduled in API response   Format: HH:MM
    private String arrivalAirport;              //mapped to 3 letter arrival.iata  in API response

    private boolean isDeleted;

}
