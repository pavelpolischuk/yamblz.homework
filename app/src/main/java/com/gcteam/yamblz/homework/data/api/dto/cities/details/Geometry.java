package com.gcteam.yamblz.homework.data.api.dto.cities.details;

import com.google.gson.annotations.SerializedName;

public class Geometry {

    @SerializedName("location")
    private Location location;

    public Location getLocation() {
        return location;
    }

}
