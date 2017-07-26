package com.gcteam.yamblz.homework.weather.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by turist on 16.07.2017.
 */

public class Wind {

    @SerializedName("speed")
    public float speed; // m/s or miles/hour

    @SerializedName("deg")
    public double directionDegree;
}
