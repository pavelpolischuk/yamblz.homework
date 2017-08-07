package com.gcteam.yamblz.homework.data.api.dto.weather.current;

import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    private int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

}
