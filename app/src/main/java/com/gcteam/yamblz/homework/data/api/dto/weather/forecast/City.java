package com.gcteam.yamblz.homework.data.api.dto.weather.forecast;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
