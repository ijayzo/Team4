package com.example.hotel_reviewsandcomments.Hotels.Controllers;

import com.example.hotel_reviewsandcomments.Hotels.DTO.AllHotelsDTO;
import com.example.hotel_reviewsandcomments.Hotels.DTO.CreateHotelPartnerDTO;
import com.example.hotel_reviewsandcomments.Hotels.DTO.HotelPartnerDTO;
import com.example.hotel_reviewsandcomments.Hotels.Services.HotelPartnerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/hotel")
public class HotelPartnerController {
    int port;
    private HotelPartnerServices hotelPartnerServices;

    @Autowired
    public HotelPartnerController(HotelPartnerServices hotelPartnerServices) {
        this.hotelPartnerServices = hotelPartnerServices;
    }

    //get hotel by hotel id
    @GetMapping("/id/{hotel_partner_id}")
    public ResponseEntity<?> getHotelByHotelId(@PathVariable Integer hotel_partner_id){

        HotelPartnerDTO hotelPartner = HotelPartnerServices.getHotelByHotelId(hotel_partner_id);

        if(hotelPartner.getHotelPartnerDTOList.isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(hotelPartner);
    }
    //getAll hotels
    @GetMapping("/all")
    public ResponseEntity<?> getALlHotels(){
        AllHotelsDTO allHotels = hotelPartnerServices.getAllHotels();
        return ResponseEntity.ok(allHotels);
    }

    //post new hotel partner
    @PostMapping("/new")
    public ResponseEntity<?> createHotelPartner(@RequestBody CreateHotelPartnerDTO createHotelPartner) throws URISyntaxException {
        hotelPartnerServices.createHotelPartner(createHotelPartner);

        return ResponseEntity.created(new URI("http://localhost:" + port + "/hotels/new/created")).build();
    }
}
