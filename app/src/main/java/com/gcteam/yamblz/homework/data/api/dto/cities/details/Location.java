package com.gcteam.yamblz.homework.data.api.dto.cities.details;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("lat")
    private double lat;
    @SerializedName("lng")
    private double lng;

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

}
