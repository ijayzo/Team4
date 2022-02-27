package com.example.demo.Services;

import com.example.demo.DAO.HotelPartnerRepository;
import com.example.demo.Models.HotelPartner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelPartnerServices {

    private HotelPartnerRepository hotelPartnerRepository;
    private List<HotelPartner> hotelPartnerList;


    @Autowired
    public HotelPartnerServices(HotelPartnerRepository hotelPartnerRepository) {
        this.hotelPartnerRepository = hotelPartnerRepository;
    }

    public HotelPartnerServices() {
    }

    public HotelPartner getHotelByHotelId(int hotel_partner_id){
        return hotelPartnerRepository.findById(hotel_partner_id).get();
    }

    public List<HotelPartner> getAllHotels(){
        hotelPartnerList = hotelPartnerRepository.findAll();
        return hotelPartnerList;
    }

    //    public int createHotelPartner(CreateHotelPartnerDTO createHotelPartner){
//        HotelPartner newHotelPartner = new HotelPartner(createHotelPartner.getHotel_partner_id(), createHotelPartner.getHotel_name(),createHotelPartner.getHotel_location());
//        return hotelPartnerRepository.save(newHotelPartner).getId();
//    }
    public void createHotelPartner(HotelPartner hotelPartner) {
        hotelPartnerRepository.save(hotelPartner);
    }
}
