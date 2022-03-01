package com.example.demo.Services;

import com.example.demo.DAO.HotelPartnerRepository;
import com.example.demo.DTO.CreateHotelPartnerRequest;
import com.example.demo.Exceptions.HotelPartnerDoesNotExist;
import com.example.demo.Models.HotelPartner;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelPartnerServices {

    private HotelPartnerRepository hotelPartnerRepository;


    private static Logger logger = LoggerFactory.getLogger(HotelPartnerServices.class);


    @Autowired
    public HotelPartnerServices(HotelPartnerRepository hotelPartnerRepository) {
        this.hotelPartnerRepository = hotelPartnerRepository;
    }

    public HotelPartnerServices() {
    }

    public HotelPartner getHotelByHotelId(int hotel_partner_id){
        HotelPartner hotelPartner = hotelPartnerRepository.findById(hotel_partner_id).get();

        if(hotelPartner == null || hotelPartner.getHotelName() == null){
            logger.error("Hotel Partner Does Not Exist");
            throw new HotelPartnerDoesNotExist();

        }
        return hotelPartner;
    }

    public List<HotelPartner> getAllHotels(){
       return hotelPartnerRepository.findAll().stream()
               .filter( hotelPartner -> hotelPartner.getIsDeleted() == false)
               .collect(Collectors.toList());

    }

    public void createHotelPartner(CreateHotelPartnerRequest createHotelPartner) {
        HotelPartner hotelPartner = new HotelPartner();
        hotelPartner.setHotelLocation(createHotelPartner.getHotelLocation());
        hotelPartner.setHotelName(createHotelPartner.getHotelName());
        int generateID = Integer.parseInt(RandomStringUtils.randomNumeric(6));
        hotelPartner.setId(generateID);
        hotelPartnerRepository.save(hotelPartner);
    }

    public void deleteHotelPartner(int hotelId){
       Optional<HotelPartner> partner =  hotelPartnerRepository.getHotelPartnerById(hotelId);
       if(partner.isPresent()){
           HotelPartner hotelPartner = new HotelPartner();
           hotelPartner.setIsDeleted(true);
           hotelPartnerRepository.save(hotelPartner);
       } else {
           throw new HotelPartnerDoesNotExist();
       }
    }
}
