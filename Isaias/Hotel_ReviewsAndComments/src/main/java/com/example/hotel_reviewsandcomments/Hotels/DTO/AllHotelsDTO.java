package com.example.hotel_reviewsandcomments.Hotels.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AllHotelsDTO {

    private List<HotelPartnerDTO> hotelPartnerDTOList;

}
