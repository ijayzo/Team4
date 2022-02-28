package com.example.hotel_reviewsandcomments.Hotels.Controllers;

import com.example.hotel_reviewsandcomments.Hotels.DTO.AllHotelsDTO;
import com.example.hotel_reviewsandcomments.Hotels.DTO.CreateHotelPartnerDTO;
import com.example.hotel_reviewsandcomments.Hotels.DTO.HotelPartnerDTO;
import com.example.hotel_reviewsandcomments.Hotels.Models.HotelPartner;
import com.example.hotel_reviewsandcomments.Hotels.Services.HotelPartnerServices;
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
    //tested
    @GetMapping("/id/{hotel_partner_id}")
    public ResponseEntity<?> getHotelByHotelId(@PathVariable Integer hotel_partner_id){
        HotelPartner hotelPartner = hotelPartnerServices.getHotelByHotelId(hotel_partner_id);

        if(hotelPartner == null || hotelPartner.getId() == null){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(hotelPartner);
    }
    //tested
    //getAll hotels
    @GetMapping("/all")
    public ResponseEntity<?> getALlHotels(){
        List<HotelPartner> allHotels = hotelPartnerServices.getAllHotels();
        return ResponseEntity.ok(allHotels);
    }
    //tested
    //post new hotel partner
    @PostMapping("/new")
    public ResponseEntity<?> createHotelPartner(@RequestBody HotelPartner createHotelPartner) throws URISyntaxException {
        hotelPartnerServices.createHotelPartner(createHotelPartner);

        return ResponseEntity.created(new URI("http://localhost:" + port + "/hotels/new/created")).build();
    }
}
