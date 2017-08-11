package com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete;

import com.google.gson.annotations.SerializedName;

public class StructuredFormatting {

    @SerializedName("main_text")
    private String mainText;
    @SerializedName("secondary_text")
    private String secondaryText;

    public String getMainText() {
        return mainText;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

}
