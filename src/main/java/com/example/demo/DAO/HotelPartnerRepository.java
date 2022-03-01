package com.example.demo.DAO;

import com.example.demo.Models.HotelPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelPartnerRepository extends JpaRepository<HotelPartner, Integer> {
    public List<HotelPartner> getAllById(Integer hotel_partner_id);
    Optional<HotelPartner> getHotelPartnerById(int hotelId);
}
