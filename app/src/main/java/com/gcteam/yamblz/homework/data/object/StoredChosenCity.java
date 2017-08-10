package com.gcteam.yamblz.homework.data.object;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Kim Michael on 07.08.17
 */
@Entity
public class StoredChosenCity {

    @PrimaryKey
    private Integer priority;
    private String cityName;
    private String userCityName;
    private double lat;
    private double lon;
    private String placeId;
    private String countryName;

    public StoredChosenCity(String cityName, String userCityName, Integer priority, double lat, double lon,
                            String placeId, String countryName) {
        this.cityName = cityName;
        this.userCityName = userCityName;
        this.priority = priority;
        this.lat = lat;
        this.lon = lon;
        this.placeId = placeId;
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUserCityName() {
        return userCityName;
    }

    public void setUserCityName(String userCityName) {
        this.userCityName = userCityName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
