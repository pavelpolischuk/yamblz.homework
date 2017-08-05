package com.gcteam.yamblz.homework.data.api.dto.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by turist on 16.07.2017.
 */

public class WeatherCondition {

    public long id;
    public String main;
    public String description;

    @SerializedName("icon")
    public String iconId;
}


