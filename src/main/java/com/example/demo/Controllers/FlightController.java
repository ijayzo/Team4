package com.example.demo.Controllers;

import com.example.demo.DTO.getDirectFromFlightRequest;
import com.example.demo.DTO.getFlightByIdRequest;
import com.example.demo.Models.FlightDBModel;
import com.example.demo.Models.FlightList;
import com.example.demo.Services.FlightServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Flights Controller
 * Able to get all flights.
 * Able to access the FlightListAPI or an employee-accessible database for flights with FROM and TO locations, respectively, locations as URL mappings
 * Able to get flights by FLight ID
 * Able to delete flights by using the FROM and TO mapping, also.
 */

@RestController
@RequestMapping("flights")
@CrossOrigin("${react.config.url}")
public class FlightController {

    @Autowired
    private FlightServices flightservice;

    @GetMapping("/getAllFlights")
    public ResponseEntity getAllFlights(){
        return ResponseEntity.status(HttpStatus.OK).body(flightservice.getAll());
    }

    @PostMapping("/getDirectFromAPI")
    public ResponseEntity getFromAPI(@RequestBody getDirectFromFlightRequest getDirectFromFlightRequest) {

        FlightList flights = flightservice.getTestFlightsfromAPI(getDirectFromFlightRequest.getFrom(),getDirectFromFlightRequest.getTo());

        return ResponseEntity.status(HttpStatus.OK).body(flights);

    }

    @PostMapping("/getFlights")
    public ResponseEntity getFlights(@RequestBody getDirectFromFlightRequest getDirectFromFlightRequest
                                  ) {

        List<FlightDBModel> theFlightList = flightservice.getFlightsRoute(getDirectFromFlightRequest.getFrom(),getDirectFromFlightRequest.getTo());

        return ResponseEntity.status(HttpStatus.OK).body(theFlightList);

    }

    @PostMapping("/getOneFlight")
    public ResponseEntity getOneFlight(@RequestBody getFlightByIdRequest flightRequest){
        FlightDBModel theFlight = flightservice.getFlightByID(flightRequest.getFlightId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(theFlight);
    }

    //TODO: DO i need this
    @DeleteMapping("/deleteFlights/from/{from}/to/{to}")
    public ResponseEntity deleteFlights(@PathVariable String from, @PathVariable String to) {

        flightservice.deleteFlightsRoute(from,to);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
