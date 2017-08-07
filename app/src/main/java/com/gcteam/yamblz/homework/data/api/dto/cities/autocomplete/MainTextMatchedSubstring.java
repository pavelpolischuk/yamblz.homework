package com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete;

import com.google.gson.annotations.SerializedName;

public class MainTextMatchedSubstring {

    @SerializedName("length")
    private int length;
    @SerializedName("offset")
    private int offset;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

}
