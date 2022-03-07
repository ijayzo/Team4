package com.example.demo.Services;

import com.example.demo.DAO.FlightsAPIDataJPA;
import com.example.demo.Models.Flight;
import com.example.demo.Models.FlightDBModel;
import com.example.demo.Models.FlightList;
import com.example.demo.Security.EmployeeJWTRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Flight Servoce class
 * Holds the access key for the flight API.  Change this value if a new account is used.
 */
@Service
public class FlightServices {


    private String API_ACCESS_KEY = "2d617ef35f6b0a021fc170b01b98bc8f";
    private static Logger logger = LoggerFactory.getLogger(FlightServices.class);

    @Autowired
    FlightsAPIDataJPA flightApiDao;
    //tested
    public List<FlightDBModel> getAll() {
        return flightApiDao.findAll();
    }

    /**
     * This method will get up to 10 flights from the API matching the parameters and return FlighList object after deserializing the response from JSON
     * @param from 3 letter IATA code for departure airport
     * @param to 3 letter IATA code for arrival airport
     * @return FlightList Object deserialize from API response
     */
    public List<FlightDBModel> getFlightsFromAPI(String from, String to) {

        //build URL string with Accesskey and Filters
        String url = "http://api.aviationstack.com/v1/flights?access_key=" + API_ACCESS_KEY + "&dep_iata=" + from + "&arr_iata=" + to + "&limit=10";
        RestTemplate theRestTemplate = new RestTemplate();

        //Initialize List of FlightEntities to be returned
        List <FlightDBModel> theFlightList = new ArrayList<>();
        FlightList response = new FlightList();

        //Get Response from API and Deserialize
        //Get Response from API and Deserialize
        logger.info(String.format("Attempting to connect to Flight API with URL -> %s", url));
        try {
            response = theRestTemplate.getForObject(url,FlightList.class);
        } catch (Exception e) {
            logger.error("API Processing or Deserialization issue.. Investigate", e);
            return null;
        }

        //loop through the flights and save to DB.
        for(Flight FlightfromAPI : response.getFlightList()) {
            FlightDBModel theFlight = new FlightDBModel();
            theFlight.setFlight_id(0);                              //save new record
            theFlight.setDep_carriercode(FlightfromAPI.getAirline().getIata());
            theFlight.setDep_fltnumber(FlightfromAPI.getNestedFlight().getNumber());
            theFlight.setDep_airport(FlightfromAPI.getDeparture().getIata());
            theFlight.setDep_fltdate(Date.valueOf(FlightfromAPI.getDeparture().getScheduled().substring(0,10)));
            theFlight.setDep_flttime(FlightfromAPI.getDeparture().getScheduled().substring(11,16));
            theFlight.setArrivalAirport(FlightfromAPI.getArrival().getIata());
            theFlight.setArrivalFlightDate(Date.valueOf(FlightfromAPI.getArrival().getScheduled().substring(0,10)));
            theFlight.setArrivalTime(FlightfromAPI.getArrival().getScheduled().substring(11,16));
            theFlight.setDeleted(false);
            flightApiDao.save(theFlight);                           // save flight to DB
            theFlightList.add(theFlight);                           //add to the List
            logger.info("Added Flight to DB from API: " + theFlight.getDep_carriercode()+ theFlight.getDep_fltnumber() + " " + theFlight.getDep_airport() + "  " + theFlight.getArrivalAirport());
        }
        return theFlightList;
    }

    /**
     * This method will get up to 10 flights from the API matching the parameters and return FlighList object as response JSON.  This is mostly used for testing the API response
     * @param from 3 letter IATA code for departure airport
     * @param to 3 letter IATA code for arrival airport
     * @return FlightList Object deserialize from API response
     */
    public FlightList getTestFlightsfromAPI(String from, String to) {
        //build URL string with Accesskey and Filters
        String url = "http://api.aviationstack.com/v1/flights?access_key=" + API_ACCESS_KEY + "&dep_iata=" + from + "&arr_iata=" + to + "&limit=10";
        RestTemplate theRestTemplate = new RestTemplate();
        FlightList response = theRestTemplate.getForObject(url,FlightList.class);
        return response;
    }

    /**
     * This method will search the DB first for any flight data for a specific route given from parameters.  If it does not find any, it will use the API to pull data and return this
     * @param from  3 letter IATA code for departure airport
     * @param to 3 letter IATA code for arrival airport
     * @return FlightList either populted from DB or from API
     */
    //tested
    public List<FlightDBModel> getFlightsRoute(String from, String to) {

        //Check the DB First if the Information is available.  If yes return that
        List<FlightDBModel> theFlightList = flightApiDao.findAllRoute(from.toUpperCase(), to.toUpperCase());

        //if There are No Flights.  Call the method to get the Flight from API
        if (theFlightList.size() == 0) {
            logger.info("No Flights Found in DB for ORIGIN: " + from.toUpperCase() + "  DESTINATION: " + to.toUpperCase());
            theFlightList = getFlightsFromAPI(from, to);
        }

        return theFlightList;
    }

    /**
     * This method will mark all flights for deleted for a specific route
     * @param from 3 letter IATA code for departure airport
     * @param to 3 letter IATA code for arrival airport
     */
    //testing
    public void deleteFlightsRoute(String from, String to) {
        flightApiDao.deleteAllRoute(from.toUpperCase(), to.toUpperCase());
    }

//tested
    public FlightDBModel getFlightByID(int id) {
        return  flightApiDao.findById(id).get();
    }
//tested
    public void deleteFlightByID(int id) {
        flightApiDao.deleteById(id);
    }

}
