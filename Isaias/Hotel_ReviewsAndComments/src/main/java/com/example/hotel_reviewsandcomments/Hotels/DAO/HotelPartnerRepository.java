package com.example.hotel_reviewsandcomments.Hotels.DAO;

import com.example.hotel_reviewsandcomments.Hotels.Models.HotelPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelPartnerRepository extends JpaRepository<HotelPartner, Integer> {
   public List<HotelPartner> getAllById(Integer hotel_partner_id);
}