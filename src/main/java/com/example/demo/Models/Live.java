package com.example.demo.Models;

public class Live {

    private String updated;
    private String latitude;
    private String longitude;
    private String altitude;
    private String direction;
    private String speed_horizontal;
    private String speed_vertical;
    private String is_ground;

    public Live(String updated, String latitude, String longitude, String altitude, String direction, String speed_horizontal, String speed_vertical, String is_ground) {
        this.updated = updated;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.direction = direction;
        this.speed_horizontal = speed_horizontal;
        this.speed_vertical = speed_vertical;
        this.is_ground = is_ground;
    }

    public Live() {
        super();
    }
    public String getUpdated() {
        return updated;
    }
    public void setUpdated(String updated) {
        this.updated = updated;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getAltitude() {
        return altitude;
    }
    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public String getSpeed_horizontal() {
        return speed_horizontal;
    }
    public void setSpeed_horizontal(String speed_horizontal) {
        this.speed_horizontal = speed_horizontal;
    }
    public String getSpeed_vertical() {
        return speed_vertical;
    }
    public void setSpeed_vertical(String speed_vertical) {
        this.speed_vertical = speed_vertical;
    }
    public String getIs_ground() {
        return is_ground;
    }
    public void setIs_ground(String is_ground) {
        this.is_ground = is_ground;
    }
    @Override
    public String toString() {
        return "Live [updated=" + updated + ", latitude=" + latitude + ", longitude=" + longitude + ", altitude="
                + altitude + ", direction=" + direction + ", speed_horizontal=" + speed_horizontal + ", speed_vertical="
                + speed_vertical + ", is_ground=" + is_ground + "]";
    }

}
