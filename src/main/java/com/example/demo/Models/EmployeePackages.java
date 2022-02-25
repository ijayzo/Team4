package com.example.demo.Models;



import com.example.demo.DTO.PackageStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.DTO.PackageCategory;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePackages {
    @Id
    private int employeePackageId;
    private int employeeId;
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
    private int hotelPartnerId;  // FK to HotelPartners
    private int fightId;  //FK to field flightId on DepartingTravelInfo
    private String packageDescription;
    private int totalPackageSignUp;
    private boolean isDeleted;

}
