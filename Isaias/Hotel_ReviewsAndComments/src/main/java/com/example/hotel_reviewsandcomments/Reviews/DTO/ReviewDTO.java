package com.example.hotel_reviewsandcomments.Reviews.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewDTO {
    private int review_comment_id;
    private int employee_id;
    private int employee_package_id;
    private String review_comments;
    private int review_score;
}
