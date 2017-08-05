
package com.gcteam.yamblz.homework.data.api.dto.cities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CitiesResponse {

    @SerializedName("predictions")
    private List<Prediction> predictions;
    @SerializedName("status")
    private String status;

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public String getStatus() {
        return status;
    }

}
