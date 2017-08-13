package com.gcteam.yamblz.homework.data.repository.cities;

import android.support.annotation.NonNull;

import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Kim Michael on 13.08.17
 */
public interface CityRepository {

    Single<List<FilteredCity>> getCitiesByFilter(@NonNull String input);

    Single<StoredCity> saveChosenCityDetails(FilteredCity filteredCity);

    void saveChosenCityDetails(StoredCity storedCity);

    Single<List<FilteredCity>> getCities();

    void saveChosenCity(StoredCity storedCity);

    void deleteCity(FilteredCity filteredCity);

    void setNoChosenCity();
}
