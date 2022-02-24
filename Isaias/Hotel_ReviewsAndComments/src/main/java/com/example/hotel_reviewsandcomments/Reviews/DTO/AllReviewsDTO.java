package com.example.hotel_reviewsandcomments.Reviews.DTO;

import com.example.hotel_reviewsandcomments.Hotels.DTO.HotelPartnerDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AllReviewsDTO {
    private List<ReviewDTO> reviewDTOList;
}
