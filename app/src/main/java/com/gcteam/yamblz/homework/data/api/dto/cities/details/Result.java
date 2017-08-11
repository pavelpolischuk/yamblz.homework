package com.gcteam.yamblz.homework.data.api.dto.cities.details;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("geometry")
    private Geometry geometry;
    @SerializedName("name")
    private String name;
    @SerializedName("place_id")
    private String placeId;

    public Geometry getGeometry() {
        return geometry;
    }

    public String getName() {
        return name;
    }

}
