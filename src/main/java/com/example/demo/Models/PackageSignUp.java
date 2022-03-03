package com.example.demo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageSignUp {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int packageSignUpId;
    private int employeeId;
    private int packageId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date signUpDate;
    private boolean isDeleted;
}
