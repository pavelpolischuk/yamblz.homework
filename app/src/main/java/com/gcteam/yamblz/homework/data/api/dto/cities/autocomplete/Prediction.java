package com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete;

import com.google.gson.annotations.SerializedName;

public class Prediction {

    @SerializedName("place_id")
    private String placeId;
    @SerializedName("structured_formatting")
    private StructuredFormatting structuredFormatting;

    public String getPlaceId() {
        return placeId;
    }

    public StructuredFormatting getStructuredFormatting() {
        return structuredFormatting;
    }

}
