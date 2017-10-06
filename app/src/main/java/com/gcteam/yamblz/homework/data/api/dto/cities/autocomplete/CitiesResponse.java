package com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CitiesResponse {

    @SerializedName("predictions")
    private List<Prediction> predictions;

    public List<Prediction> getPredictions() {
        return predictions;
    }

}
