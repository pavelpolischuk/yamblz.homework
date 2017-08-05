package com.gcteam.yamblz.homework.data.repository.cities;

import android.support.annotation.NonNull;

import com.gcteam.yamblz.homework.data.api.dto.cities.CitiesResponse;
import com.gcteam.yamblz.homework.data.local.cities.CitiesStorage;
import com.gcteam.yamblz.homework.data.network.cities.CitiesService;

import io.reactivex.Single;

/**
 * Created by Kim Michael on 03.08.17
 */
public class CitiesRepository {

    private CitiesStorage citiesStorage;
    private CitiesService citiesService;

    public CitiesRepository(CitiesStorage citiesStorage,
                     CitiesService citiesService) {
        this.citiesStorage = citiesStorage;
        this.citiesService = citiesService;
    }

    @NonNull
    public Single<CitiesResponse> getCitiesByFilter(@NonNull String input) {
        return citiesService.getSuggestionsByInput(input);
    }
}
