package com.gcteam.yamblz.homework.data.api.dto.weather.forecast;

import com.google.gson.annotations.SerializedName;

public class ForecastResponse {

    @SerializedName("city")
    private City city;
    @SerializedName("cod")
    private String cod;
    @SerializedName("message")
    private double message;
    @SerializedName("cnt")
    private int cnt;
    @SerializedName("list")
    private java.util.List<Description> description = null;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public java.util.List<Description> getDescriptions() {
        return description;
    }

    public void setDescription(java.util.List<Description> description) {
        this.description = description;
    }

}
