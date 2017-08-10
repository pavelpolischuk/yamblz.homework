package com.gcteam.yamblz.homework.data.local.cities;

import com.gcteam.yamblz.homework.data.local.AppDatabase;
import com.gcteam.yamblz.homework.data.object.StoredChosenCity;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import java.util.List;

import io.reactivex.Single;


/**
 * Created by Kim Michael on 03.08.17
 */
public class CityStorage {

    private final AppDatabase appDatabase;
    private final PreferencesManager preferencesManager;

    public CityStorage(PreferencesManager preferencesManager,
                       AppDatabase appDatabase) {
        this.preferencesManager = preferencesManager;
        this.appDatabase = appDatabase;
    }

    public void saveCityDetails(StoredChosenCity storedChosenCity) {
        appDatabase.chosenCityDAO().insert(storedChosenCity);
    }

    public Single<List<StoredChosenCity>> getChosenCities() {
        return appDatabase.chosenCityDAO().getAll();
    }

    public Single<StoredChosenCity> getChosenCity(FilteredCity chosenCity) {
        return appDatabase.chosenCityDAO().get(chosenCity.getPlaceId());
    }

    public void deleteCity(FilteredCity filteredCity) {
        appDatabase.chosenCityDAO().deleteCity(filteredCity.getPlaceId());
    }
}
