package com.gcteam.yamblz.homework.data.api.dto.weather.forecast;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("id")
    private int id;
    @SerializedName("description")
    private String description;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
