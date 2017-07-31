package com.gcteam.yamblz.homework.data.api.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by turist on 16.07.2017.
 */

public class Weather {

    @SerializedName("coord")
    public Coordinates coordinates;

    @SerializedName("weather")
    public ArrayList<WeatherCondition> conditions =  new ArrayList<>();

    public Wind wind;
    public MainDto main;

    @SerializedName("name")
    public String cityName;
}