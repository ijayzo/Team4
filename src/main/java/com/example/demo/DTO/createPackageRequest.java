package com.example.demo.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
public class createPackageRequest {

    @Enumerated(EnumType.STRING)
    private PackageCategory packageCategory;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date travelDate;
    private String travelDestination;
    private int packageDays;
    @Enumerated(EnumType.STRING)
    private PackageStatus packageStatus;
    private double packageCost;
    private String packageDescription;
    private int totalPackageSignUp;
    private boolean isDeleted;

}
