
package com.gcteam.yamblz.homework.data.api.dto.cities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StructuredFormatting {

    @SerializedName("main_text")
    private String mainText;
    @SerializedName("main_text_matched_substrings")
    private List<MainTextMatchedSubstring> mainTextMatchedSubstrings = null;
    @SerializedName("secondary_text")
    private String secondaryText;

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public List<MainTextMatchedSubstring> getMainTextMatchedSubstrings() {
        return mainTextMatchedSubstrings;
    }

    public void setMainTextMatchedSubstrings(List<MainTextMatchedSubstring> mainTextMatchedSubstrings) {
        this.mainTextMatchedSubstrings = mainTextMatchedSubstrings;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public void setSecondaryText(String secondaryText) {
        this.secondaryText = secondaryText;
    }

}
