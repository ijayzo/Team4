package com.example.demo.Controllers;

import com.example.demo.Models.HotelPartner;
import com.example.demo.Services.HotelPartnerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/hotel")
public class HotelPartnerController {

    int port;
    private HotelPartnerServices hotelPartnerServices;

    @Autowired
    public HotelPartnerController(HotelPartnerServices hotelPartnerServices) {
        this.hotelPartnerServices = hotelPartnerServices;
    }

    // TODO Dont want to use PathVariable, change please - for Richmond
    //get hotel by hotel id
    @GetMapping("/id/{hotel_partner_id}")
    public ResponseEntity<?> getHotelByHotelId(@PathVariable Integer hotel_partner_id){
        HotelPartner hotelPartner = hotelPartnerServices.getHotelByHotelId(hotel_partner_id);

        if(hotelPartner == null || hotelPartner.getHotelName() == null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(hotelPartner);
    }

    //getAll hotels
    @GetMapping("/all")
    public ResponseEntity<?> getALlHotels(){
        List<HotelPartner> allHotels = hotelPartnerServices.getAllHotels();
        return ResponseEntity.ok(allHotels);
    }

    //post new hotel partner
    //TODO: Change the Code -- For Richmond
    @PostMapping("/new")
    public ResponseEntity<?> createHotelPartner(@RequestBody HotelPartner createHotelPartner) throws URISyntaxException {
        hotelPartnerServices.createHotelPartner(createHotelPartner);

        return ResponseEntity.created(new URI("http://localhost:" + port + "/hotels/new/created")).build();
    }
}
