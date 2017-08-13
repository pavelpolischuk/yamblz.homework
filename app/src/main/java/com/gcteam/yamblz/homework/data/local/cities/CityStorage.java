package com.gcteam.yamblz.homework.data.local.cities;

import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Kim Michael on 13.08.17
 */
public interface CityStorage {

    void saveCityDetails(StoredCity storedCity);

    Single<List<StoredCity>> getChosenCities();

    Single<StoredCity> getChosenCity(FilteredCity chosenCity);

    void deleteCity(FilteredCity filteredCity);
}
