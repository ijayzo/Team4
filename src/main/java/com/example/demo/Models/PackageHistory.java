package com.example.demo.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageHistory {

    @Id
    private int packageHistoryId;
    private int packageId; // FK to EmployeePackages
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    private String eventDescription;


}
