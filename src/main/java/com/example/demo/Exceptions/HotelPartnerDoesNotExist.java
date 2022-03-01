package com.example.demo.Exceptions;

public class HotelPartnerDoesNotExist extends RuntimeException{
    public HotelPartnerDoesNotExist(){
        super("Hotel Does not Exist");
    }
}
