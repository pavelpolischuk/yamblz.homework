package com.gcteam.yamblz.homework.data.api.dto.cities.details;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photo {

    @SerializedName("height")
    private int height;
    @SerializedName("html_attributions")
    private List<String> htmlAttributions = null;
    @SerializedName("photo_reference")
    private String photoReference;
    @SerializedName("width")
    private int width;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
