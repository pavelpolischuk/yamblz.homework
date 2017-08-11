package com.gcteam.yamblz.homework.data.api.dto.cities.details;

import com.google.gson.annotations.SerializedName;

public class CityDetailsResponse {

    @SerializedName("result")
    private Result result;

    public Result getResult() {
        return result;
    }
}
