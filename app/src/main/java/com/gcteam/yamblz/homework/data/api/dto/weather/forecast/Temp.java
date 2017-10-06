package com.gcteam.yamblz.homework.data.api.dto.weather.forecast;

import com.google.gson.annotations.SerializedName;

public class Temp {

    @SerializedName("day")
    private double day;
    @SerializedName("min")
    private double min;
    @SerializedName("max")
    private double max;

    public double getDay() {
        return day;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

}
