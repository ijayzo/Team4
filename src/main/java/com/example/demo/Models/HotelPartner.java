package com.example.demo.Models;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class HotelPartner {

    @Id
    @Column(nullable = false)
    private int id;
    private String hotelLocation;
    private String hotelName;
    @Column(nullable = false)
    private Boolean isDeleted = false;


}
