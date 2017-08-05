package com.gcteam.yamblz.homework.data.network.cities;

import com.gcteam.yamblz.homework.data.api.GooglePlacesAPI;
import com.gcteam.yamblz.homework.data.api.dto.cities.CitiesResponse;

import java.util.Locale;

import io.reactivex.Single;

/**
 * Created by Kim Michael on 04.08.17
 */
public class CitiesService {

    private GooglePlacesAPI googlePlacesAPI;

    private static final String TYPES_CITIES = "(cities)";

    public CitiesService(GooglePlacesAPI googlePlacesAPI) {
        this.googlePlacesAPI = googlePlacesAPI;
    }

    public Single<CitiesResponse> getSuggestionsByInput(String input) {
        return googlePlacesAPI.getSuggestionsByInput(
                GooglePlacesAPI.API_KEY,
                input,
                TYPES_CITIES,
                Locale.getDefault().getDisplayLanguage());
    }
}
