package com.example.demo.Controllers;

import com.example.demo.Models.FlightDBModel;
import com.example.demo.Models.FlightList;
import com.example.demo.Services.FlightServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("flights")
public class FlightController {

    @Autowired
    private FlightServices flightservice;

    @GetMapping("/getAllFlights")
    public ResponseEntity getAllFlights(){
        return ResponseEntity.status(HttpStatus.OK).body(flightservice.getAll());
    }

    @GetMapping("/getDirectFromAPI/from/{from}/to/{to}")
    public ResponseEntity getFromAPI(@PathVariable String from, @PathVariable String to,
                                     @RequestHeader Map<String, String> headers) {

        FlightList flights = flightservice.getTestFlightsfromAPI(from,to);

        return ResponseEntity.status(HttpStatus.OK).body(flights);

    }

    @GetMapping("/getFlights/from/{from}/to/{to}")
    public ResponseEntity getFlights(@PathVariable String from, @PathVariable String to,
                                     @RequestHeader Map<String, String> headers) {

        List<FlightDBModel> theFlightList = flightservice.getFlightsRoute(from,to);

        return ResponseEntity.status(HttpStatus.OK).body(theFlightList);

    }

    @GetMapping("/getOneFlight/{flightID}")
    public ResponseEntity getOneFlight(@PathVariable int flightID){
        FlightDBModel theFlight = flightservice.getFlightByID(flightID);
        return ResponseEntity.status(HttpStatus.OK)
                .body(theFlight);
    }

    @DeleteMapping("/deleteFlights/from/{from}/to/{to}")
    public ResponseEntity deleteFlights(@PathVariable String from, @PathVariable String to) {

        flightservice.deleteFlightsRoute(from,to);

        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
