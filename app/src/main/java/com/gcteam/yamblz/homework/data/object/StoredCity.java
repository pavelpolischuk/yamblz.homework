package com.gcteam.yamblz.homework.data.object;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Kim Michael on 07.08.17
 */
@Entity
public class StoredCity {

    @PrimaryKey
    private final String placeId;
    private final Integer priority;
    private final String cityName;
    private final String userCityName;
    private final double lat;
    private final double lng;
    private final String countryName;

    public StoredCity(String cityName, String userCityName, Integer priority, double lat, double lng,
                      String placeId, String countryName) {
        this.cityName = cityName;
        this.userCityName = userCityName;
        this.priority = priority;
        this.lat = lat;
        this.lng = lng;
        this.placeId = placeId;
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getUserCityName() {
        return userCityName;
    }

    public Integer getPriority() {
        return priority;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getCountryName() {
        return countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoredCity that = (StoredCity) o;

        if (Double.compare(that.lat, lat) != 0) return false;
        if (Double.compare(that.lng, lng) != 0) return false;
        if (placeId != null ? !placeId.equals(that.placeId) : that.placeId != null) return false;
        if (priority != null ? !priority.equals(that.priority) : that.priority != null)
            return false;
        if (cityName != null ? !cityName.equals(that.cityName) : that.cityName != null)
            return false;
        if (userCityName != null ? !userCityName.equals(that.userCityName) : that.userCityName != null)
            return false;
        return countryName != null ? countryName.equals(that.countryName) : that.countryName == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = placeId != null ? placeId.hashCode() : 0;
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (cityName != null ? cityName.hashCode() : 0);
        result = 31 * result + (userCityName != null ? userCityName.hashCode() : 0);
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StoredCity{" +
                "placeId='" + placeId + '\'' +
                ", priority=" + priority +
                ", cityName='" + cityName + '\'' +
                ", userCityName='" + userCityName + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
