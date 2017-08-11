package com.gcteam.yamblz.homework.data.local.cities;

import com.gcteam.yamblz.homework.data.local.AppDatabase;
import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.List;

import io.reactivex.Single;


/**
 * Created by Kim Michael on 03.08.17
 */
public class CityStorage {

    private final AppDatabase appDatabase;

    public CityStorage(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public void saveCityDetails(StoredCity storedCity) {
        appDatabase.chosenCityDAO().insert(storedCity);
    }

    public Single<List<StoredCity>> getChosenCities() {
        return appDatabase.chosenCityDAO().getAll();
    }

    public Single<StoredCity> getChosenCity(FilteredCity chosenCity) {
        return appDatabase.chosenCityDAO().get(chosenCity.getPlaceId());
    }

    public void deleteCity(FilteredCity filteredCity) {
        appDatabase.chosenCityDAO().deleteCity(filteredCity.getPlaceId());
    }
}
