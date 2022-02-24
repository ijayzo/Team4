package com.example.hotel_reviewsandcomments.Hotels.Models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "hotel_partners")
public class HotelPartner {
    @Id
    @Column(name = "hotel_partner_id", nullable = false)
    private Integer id;

    @Column(name = "hotel_location")
    private String hotelLocation;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    public HotelPartner(int hotel_partner_id, String hotel_name, String hotel_location) {
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}