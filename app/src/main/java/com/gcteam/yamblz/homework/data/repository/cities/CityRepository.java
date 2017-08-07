package com.gcteam.yamblz.homework.data.repository.cities;

import android.support.annotation.NonNull;

import com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete.CitiesResponse;
import com.gcteam.yamblz.homework.data.api.dto.cities.details.CityDetailsResponse;
import com.gcteam.yamblz.homework.data.local.cities.CityStorage;
import com.gcteam.yamblz.homework.data.network.cities.CityService;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import io.reactivex.Single;

/**
 * Created by Kim Michael on 03.08.17
 */
public class CityRepository {

    private CityStorage cityStorage;
    private CityService cityService;

    public CityRepository(CityStorage cityStorage,
                          CityService cityService) {
        this.cityStorage = cityStorage;
        this.cityService = cityService;
    }

    @NonNull
    public Single<CitiesResponse> getCitiesByFilter(@NonNull String input) {
        return cityService.getSuggestionsByInput(input);
    }

    public Single<CityDetailsResponse> getCityDetails(FilteredCity chosenCity) {
        return cityService.getCityDetails(chosenCity);
    }

    public void saveCityDetails(CityDetailsResponse cityDetailsResponse) {
        cityStorage.saveCityDetails(cityDetailsResponse);
    }
}
