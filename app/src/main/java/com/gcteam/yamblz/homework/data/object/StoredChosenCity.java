package com.gcteam.yamblz.homework.data.object;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Kim Michael on 07.08.17
 */
@Entity
public class StoredChosenCity {

    @PrimaryKey
    private Long _id;
    private String cityName;
    private String userCityName;
    private double lat;
    private double lon;
    private String placeId;
    private String countryName;

    public StoredChosenCity(String cityName, String userCityName, Long _id, double lat, double lon,
                            String placeId, String countryName) {
        this.cityName = cityName;
        this.userCityName = userCityName;
        this._id = _id;
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

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
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
