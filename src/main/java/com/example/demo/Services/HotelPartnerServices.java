package com.example.demo.Services;

import com.example.demo.DAO.HotelPartnerRepository;
import com.example.demo.DTO.CreateHotelPartnerRequest;
import com.example.demo.Exceptions.HotelPartnerDoesNotExist;
import com.example.demo.Models.HotelPartnerT;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public HotelPartnerT getHotelByHotelId(int hotel_partner_id){
        HotelPartnerT hotelPartner = hotelPartnerRepository.findById(hotel_partner_id).get();

        if(hotelPartner == null || hotelPartner.getHotelName() == null){
            logger.error("Hotel Partner Does Not Exist");
            throw new HotelPartnerDoesNotExist();

        }
        return hotelPartner;
    }

    public List<HotelPartnerT> getAllHotels(){
       return hotelPartnerRepository.findAll().stream()
               .filter( hotelPartner -> hotelPartner.getIsDeleted() == false)
               .collect(Collectors.toList());

    }

    public void createHotelPartner(CreateHotelPartnerRequest createHotelPartner) {
        HotelPartnerT hotelPartner = new HotelPartnerT();
        hotelPartner.setHotelLocation(createHotelPartner.getHotelLocation());
        hotelPartner.setHotelName(createHotelPartner.getHotelName());
        int generateID = Integer.parseInt(RandomStringUtils.randomNumeric(6));
        hotelPartner.setId(generateID);
        hotelPartnerRepository.save(hotelPartner);
    }

    public void deleteHotelPartner(int hotelId){
       Optional<HotelPartnerT> partner =  hotelPartnerRepository.getHotelPartnerById(hotelId);
       if(partner.isPresent()){
           HotelPartnerT hotelPartner = partner.get();
           hotelPartner.setIsDeleted(true);
           hotelPartnerRepository.save(hotelPartner);
       } else {
           throw new HotelPartnerDoesNotExist();
       }
    }
}
