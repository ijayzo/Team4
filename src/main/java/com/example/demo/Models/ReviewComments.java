package com.example.demo.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewComments {

    @Id
    private int reviewCommentId;
    private int employeeId; //FK to the employee
    private int employeePackageId; //FK to the employeePackage
    private int reviewScore;  // 1-5
    private String reviewComments;
    private boolean isDeleted;
}
