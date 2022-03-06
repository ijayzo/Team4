package com.example.demo.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class PackageSignUpRequest {

    private int packageId;
    private String username;
    private int flightId;
    private int hotelId;

}
