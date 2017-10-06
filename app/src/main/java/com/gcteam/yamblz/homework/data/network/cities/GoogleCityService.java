package com.gcteam.yamblz.homework.data.network.cities;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.gcteam.yamblz.homework.data.api.GooglePlacesAPI;
import com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete.CitiesResponse;
import com.gcteam.yamblz.homework.data.api.dto.cities.details.CityDetailsResponse;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.Locale;

import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by Kim Michael on 04.08.17
 */
public class GoogleCityService implements CityService {

    private static final String TYPES_CITIES = "(cities)";
    private GooglePlacesAPI googlePlacesAPI;

    public GoogleCityService(GooglePlacesAPI googlePlacesAPI) {
        this.googlePlacesAPI = googlePlacesAPI;
    }

    @WorkerThread
    @NonNull
    public Single<CitiesResponse> getSuggestionsByInput(String input) {
        Timber.d("Getting suggestions for input : %s", input);
        return googlePlacesAPI.getSuggestionsByInput(
                GooglePlacesAPI.API_KEY,
                input,
                TYPES_CITIES,
                Locale.getDefault().getLanguage());
    }

    @WorkerThread
    @NonNull
    public Single<CityDetailsResponse> getCityDetails(FilteredCity chosenCity) {
        Timber.d("Gettings city details for city : %s", chosenCity.getCityName());
        return googlePlacesAPI.getCityDetails(
                GooglePlacesAPI.API_KEY,
                chosenCity.getPlaceId(),
                Locale.getDefault().getLanguage()
        );
    }

}
