package com.example.hotel_reviewsandcomments.Hotels.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotelPartnerDTO {
    private int hotel_partner_id;
    private String hotel_name;
    private String hotel_location;
}
