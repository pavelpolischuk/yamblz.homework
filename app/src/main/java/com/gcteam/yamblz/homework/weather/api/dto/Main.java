package com.gcteam.yamblz.homework.weather.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by turist on 16.07.2017.
 */

public class Main {

    @SerializedName("temp")
    public float temperature;
    public int pressure; // hPa
    public int humidity; // %
    public float temp_min;
    public float temp_max;
}
