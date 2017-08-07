package com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Prediction {

    @SerializedName("description")
    private String description;
    @SerializedName("id")
    private String id;
    @SerializedName("matched_substrings")
    private List<MatchedSubstring> matchedSubstrings = null;
    @SerializedName("place_id")
    private String placeId;
    @SerializedName("reference")
    private String reference;
    @SerializedName("structured_formatting")
    private StructuredFormatting structuredFormatting;
    @SerializedName("terms")
    private List<Term> terms = null;
    @SerializedName("types")
    private List<String> types = null;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MatchedSubstring> getMatchedSubstrings() {
        return matchedSubstrings;
    }

    public void setMatchedSubstrings(List<MatchedSubstring> matchedSubstrings) {
        this.matchedSubstrings = matchedSubstrings;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public StructuredFormatting getStructuredFormatting() {
        return structuredFormatting;
    }

    public void setStructuredFormatting(StructuredFormatting structuredFormatting) {
        this.structuredFormatting = structuredFormatting;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

}
