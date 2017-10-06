package com.gcteam.yamblz.homework.data.object;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Kim Michael on 07.08.17
 */
@Entity
public class StoredCity {

    @PrimaryKey
    private final String placeId;
    @NonNull
    private final String cityName;
    @NonNull
    private final String userCityName;
    private final double lat;
    private final double lng;
    @NonNull
    private final String countryName;
    @Nullable
    private Integer priority;

    public StoredCity(@NonNull String cityName,
                      @NonNull String userCityName,
                      @Nullable Integer priority,
                      double lat, double lng,
                      @NonNull String placeId,
                      @NonNull String countryName) {
        this.cityName = cityName;
        this.userCityName = userCityName;
        this.priority = priority;
        this.lat = lat;
        this.lng = lng;
        this.placeId = placeId;
        this.countryName = countryName;
    }

    @NonNull
    public String getCityName() {
        return cityName;
    }

    @NonNull
    public String getUserCityName() {
        return userCityName;
    }

    @Nullable
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(@Nullable Integer priority) {
        this.priority = priority;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @NonNull
    public String getPlaceId() {
        return placeId;
    }

    @NonNull
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
        if (!cityName.equals(that.cityName)) return false;
        if (!userCityName.equals(that.userCityName)) return false;
        return countryName.equals(that.countryName);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = placeId != null ? placeId.hashCode() : 0;
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + cityName.hashCode();
        result = 31 * result + userCityName.hashCode();
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + countryName.hashCode();
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
