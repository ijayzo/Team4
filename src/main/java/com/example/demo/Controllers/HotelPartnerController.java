package com.example.demo.Controllers;

import com.example.demo.DTO.CreateHotelPartnerRequest;
import com.example.demo.DTO.GetHotelPartnerIdRequest;
import com.example.demo.Models.HotelPartnerT;
import com.example.demo.Services.HotelPartnerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Hotel Controller
 * Able to get hotels by ID, get all hotels, post a new hotel partner, or delete a hotel partner.
 */
@RestController
@CrossOrigin("${react.config.url}") // TODO: Dont hard Code this
@RequestMapping("/hotel")
public class HotelPartnerController {

    private HotelPartnerServices hotelPartnerServices;

    @Autowired
    public HotelPartnerController(HotelPartnerServices hotelPartnerServices) {
        this.hotelPartnerServices = hotelPartnerServices;
    }


    //get hotel by hotel id
    @PostMapping("/get")
    public ResponseEntity getHotelByHotelId(@RequestBody GetHotelPartnerIdRequest getHotelPartner){
        return ResponseEntity.ok( hotelPartnerServices.getHotelByHotelId(getHotelPartner.getHotelPartnerId()));
    }

    //getAll hotels
    @GetMapping("/all")
    public ResponseEntity getALlHotels(){
        List<HotelPartnerT> allHotels = hotelPartnerServices.getAllHotels();
        return ResponseEntity.ok(allHotels);
    }

    //post new hotel partner
    //TODO: Change the Code -- For Richmond
    @PostMapping("/new")
    public ResponseEntity createHotelPartner(@RequestBody CreateHotelPartnerRequest createHotelPartner)  {
        hotelPartnerServices.createHotelPartner(createHotelPartner);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/delete")
    public ResponseEntity deleteHotelPartner (@RequestBody GetHotelPartnerIdRequest hotelPartnerIdRequest){
        hotelPartnerServices.deleteHotelPartner(hotelPartnerIdRequest.getHotelPartnerId());

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
