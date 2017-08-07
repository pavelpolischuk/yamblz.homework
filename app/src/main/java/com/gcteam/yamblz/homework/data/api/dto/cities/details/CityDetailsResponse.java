package com.gcteam.yamblz.homework.data.api.dto.cities.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityDetailsResponse {

    @SerializedName("html_attributions")
    private List<Object> htmlAttributions = null;
    @SerializedName("result")
    private Result result;
    @SerializedName("status")
    private String status;

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
