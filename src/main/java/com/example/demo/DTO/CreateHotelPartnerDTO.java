package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateHotelPartnerDTO {

    private int hotel_partner_id;
    private String hotel_name;
    private String hotel_location;
}
