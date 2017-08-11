package com.gcteam.yamblz.homework.data.network.cities;

import com.gcteam.yamblz.homework.data.api.GooglePlacesAPI;
import com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete.CitiesResponse;
import com.gcteam.yamblz.homework.data.api.dto.cities.details.CityDetailsResponse;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.Locale;

import io.reactivex.Single;

/**
 * Created by Kim Michael on 04.08.17
 */
public class CityService {

    static final String TYPES_CITIES = "(cities)";
    private GooglePlacesAPI googlePlacesAPI;

    public CityService(GooglePlacesAPI googlePlacesAPI) {
        this.googlePlacesAPI = googlePlacesAPI;
    }

    public Single<CitiesResponse> getSuggestionsByInput(String input) {
        return googlePlacesAPI.getSuggestionsByInput(
                GooglePlacesAPI.API_KEY,
                input,
                TYPES_CITIES,
                Locale.getDefault().getLanguage());
    }

    public Single<CityDetailsResponse> getCityDetails(FilteredCity chosenCity) {
        return googlePlacesAPI.getCityDetails(
                GooglePlacesAPI.API_KEY,
                chosenCity.getPlaceId(),
                Locale.getDefault().getLanguage()
        );
    }

}
