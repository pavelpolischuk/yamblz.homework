package com.gcteam.yamblz.homework.data.api.dto.weather.current;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("id")
    private int id;
    @SerializedName("description")
    private String description;
    @SerializedName("icon")
    private String icon;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
