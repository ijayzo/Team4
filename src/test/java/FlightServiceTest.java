import com.example.demo.DAO.FlightsAPIDataJPA;
import com.example.demo.Models.*;
import com.example.demo.Services.FlightServices;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FlightServiceTest {
    @Mock
    private FlightsAPIDataJPA flightsRepo;
    @InjectMocks
    private FlightServices flightServices;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private FlightDBModel newFlight (){
        FlightDBModel flight = new FlightDBModel();
        flight.setFlight_id(46);
        flight.setDep_carriercode("ff");
        flight.setDep_fltnumber("13");
        flight.setDep_fltdate(Date.valueOf("2022-01-05"));
        flight.setDep_flttime("14:30");
        flight.setDep_airport("MIA");
        flight.setArrivalFlightDate(Date.valueOf("2022-05-08"));
        flight.setArrivalTime("3:30");
        flight.setArrivalAirport("LAX");
        flight.setDeleted(false);
        return flight;
    }

    //deleteById
    //so close
//    @Test
//    public void shouldDeleteById(){
//        FlightDBModel flights = new FlightDBModel();
//        flights.setFlight_id(56);
//        flights.setDep_carriercode("gf");
//        flights.setDep_fltnumber("123");
//        flights.setDep_fltdate(Date.valueOf("2022-01-01"));
//        flights.setDep_flttime("16:30");
//        flights.setDep_airport("LAX");
//        flights.setArrivalFlightDate(Date.valueOf("2022-02-03"));
//        flights.setArrivalTime("23:30");
//        flights.setArrivalAirport("MIA");
//
//        when(flightsRepo.findById(flights.getFlight_id())).thenReturn(Optional.of(flights));
//
//        flightServices.deleteFlightByID(flights.getFlight_id());
//
//        verify(flightsRepo).save(flights);
//
//        ArgumentCaptor<FlightDBModel> captor = ArgumentCaptor.forClass(FlightDBModel.class);
//        verify(flightsRepo).save(captor.capture());
//        Assert.assertEquals(captor.getValue().getFlight_id(), 56);
//    }
    @Test
    public void getFlightById() {
        Optional<FlightDBModel> getFlight = flightsRepo.findById(46);
        Assert.assertEquals(newFlight().getDep_carriercode(), "ff");
        Assert.assertEquals(newFlight().getDep_fltnumber(), "13");
        Assert.assertEquals(newFlight().getDep_fltdate(), Date.valueOf("2022-01-05"));
        Assert.assertEquals(newFlight().getDep_flttime(), "14:30");
        Assert.assertEquals(newFlight().getDep_airport(), "MIA");
        Assert.assertEquals(newFlight().getArrivalFlightDate(), Date.valueOf("2022-05-08"));
        Assert.assertEquals(newFlight().getArrivalTime(), "3:30");
        Assert.assertEquals(newFlight().getArrivalAirport(), "LAX");
    }
    // findByRoute
    @Test
    public void shouldReturnByRoute(){
        List<FlightDBModel> flightDBModelList = flightsRepo.findAllRoute("Los_Angeles","New_York");
        when(flightsRepo.findAll()).thenReturn(flightDBModelList);
        assertTrue(flightDBModelList.isEmpty());
    }
    @Test
    public void shouldReturnAll(){
        when(flightsRepo.findAll()).thenReturn(Collections.emptyList());
        List<FlightDBModel> flightDBModelList = flightServices.getAll();
        assertTrue(flightDBModelList.isEmpty());
    }
//    //getFlightsFromApi
//    @Test


    //getTestFlightFromApi
//    should be same as last
//    @Test

        //not sure
//    @Test
//    public void shouldDeleteRoutes(){
//        List<FlightDBModel> flightDBModelList = flightsRepo.findAllRoute("Los_Angeles","New_York");
//        when(flightDBModelList).thenReturn(flightDBModelList);
//        flightServices.deleteFlightsRoute();
//
//        ArgumentCaptor<FlightDBModel> captor = ArgumentCaptor.forClass(FlightDBModel.class);
//        verify(flightsRepo).save(captor.capture());
//        Assert.assertEquals(captor.getAllValues(), null);
//    }

}





// flight.setFlight_date(Date.valueOf("2022-03-28"));
//         flight.setFlight_status("scheduled");
//         flight.setDeparture(new DepartureArrival( "LA","west", "iata", "ICAO", "terminal", "gate", "delay",
//         "schedule", "estimated", "Actual", "runway", "mis"));
//         flight.setArrival(new DepartureArrival(("Los Angeles International", "Western", "LAX", "KLAX", 5, 59, null,
//         "2022-02-27T00:49:00+00:00", "2022-02-27T00:49:00+00:00", null, null, null));
//         flight.setAirline(new Airline("LAX","df","fdf"));
//         flight.setNestedFlight(new NestedFlight("LAX","df","fdf",new Codeshared("c","o","d","e","s","h")));
//         flight.setAircraft(new Aircraft("register", "LAX","df","fdf"));
//         flight.setLive(new Live("1","2","3","4","5","6","7","8"));