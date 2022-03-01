package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
