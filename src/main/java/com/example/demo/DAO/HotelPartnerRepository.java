package com.example.demo.DAO;

import com.example.demo.Models.HotelPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelPartnerRepository extends JpaRepository<HotelPartner, Integer> {
    public List<HotelPartner> getAllById(Integer hotel_partner_id);
}
