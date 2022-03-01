package com.example.demo.DAO;

import com.example.demo.Models.HotelPartnerT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelPartnerRepository extends JpaRepository<HotelPartnerT, Integer> {
    public List<HotelPartnerT> getAllById(Integer hotel_partner_id);
    Optional<HotelPartnerT> getHotelPartnerById(int hotelId);
}
