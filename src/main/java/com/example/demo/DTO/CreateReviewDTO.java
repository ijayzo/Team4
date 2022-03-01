package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateReviewDTO {
    private int employeeId;
    private int employeePackageId;
    private String reviewComments;
    private int reviewScore;
}
