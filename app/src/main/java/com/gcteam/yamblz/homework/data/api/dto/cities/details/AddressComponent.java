package com.gcteam.yamblz.homework.data.api.dto.cities.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressComponent {

    @SerializedName("long_name")
    private String longName;
    @SerializedName("short_name")
    private String shortName;
    @SerializedName("types")
    private List<String> types = null;

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

}
