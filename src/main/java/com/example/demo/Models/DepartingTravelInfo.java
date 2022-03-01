package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

//TODO: Do we really need this??
//TODO: Since we have arrival in can we change the clsss name to suit it.

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartingTravelInfo {

    @Id
    private int departureTravelInfoId;
    //TODO: Needed???
    //private int employeeId;
    private int flightId;
    private String depatureFlight;
    private int depatureFlightNumber;
    @DateTimeFormat(pattern = "yyyy-Mm-dd")
    @Temporal(TemporalType.DATE)
    private Date depatureFlightDate;
    private String depatureAirport;
    @DateTimeFormat(pattern = "Hh:mm:ss")
    @Temporal(TemporalType.TIME)
    private Date depatureTime;
    @DateTimeFormat(pattern = "yyyy-Mm-dd")
    @Temporal(TemporalType.DATE)
    private Date arrivalFlightDate;
    private String arrivalAirport;
    @DateTimeFormat(pattern = "Hh:mm:ss")
    @Temporal(TemporalType.TIME)
    private Date arrivalTime;
    private boolean isDeleted;


}
