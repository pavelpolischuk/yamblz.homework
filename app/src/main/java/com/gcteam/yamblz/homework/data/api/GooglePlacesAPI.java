package com.gcteam.yamblz.homework.data.api;

import com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete.CitiesResponse;
import com.gcteam.yamblz.homework.data.api.dto.cities.details.CityDetailsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kim Michael on 03.08.17
 */
public interface GooglePlacesAPI {

    String API_BASE_URL = "https://maps.googleapis.com/maps/api/place/";
    String API_KEY = "AIzaSyA0x--xPieNHJJ7NsWhTb4E-D9eoc_bH34";

    @GET("autocomplete/json")
    Single<CitiesResponse> getSuggestionsByInput(
            @Query("key") String APIKey,
            @Query("input") String input,
            @Query("types") String types,
            @Query("language") String language
    );

    @GET("details/json")
    Single<CityDetailsResponse> getCityDetails(
            @Query("key") String APIKey,
            @Query("placeid") String placeId,
            @Query("language") String language
    );
}
