package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelPartners {
    @Id
    private int hotelPartnerId;
    private String hotelName;
    private String hotelLocation;
    private boolean isDeleted;
}
