package com.gcteam.yamblz.homework.data.local.cities;

import android.support.annotation.WorkerThread;

import com.gcteam.yamblz.homework.data.local.AppDatabase;
import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.List;

import io.reactivex.Single;
import timber.log.Timber;


/**
 * Created by Kim Michael on 03.08.17
 */
public class RoomCityStorage implements CityStorage {

    private final AppDatabase appDatabase;

    public RoomCityStorage(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    public void saveCityDetails(StoredCity storedCity) {
        Timber.d("Saving city details for city : %s", storedCity.getCityName());
        appDatabase.chosenCityDAO().insert(storedCity);
    }

    @WorkerThread
    public Single<List<StoredCity>> getChosenCities() {
        Timber.d("Getting all chosen cities");
        return appDatabase.chosenCityDAO().getAll();
    }

    @WorkerThread
    public Single<StoredCity> getChosenCity(FilteredCity chosenCity) {
        Timber.d("Getting stored city for city : %s", chosenCity.getCityName());
        return appDatabase.chosenCityDAO().get(chosenCity.getPlaceId());
    }

    @WorkerThread
    public void deleteCity(FilteredCity filteredCity) {
        Timber.d("Deleting city : %s", filteredCity.getCityName());
        StoredCity storedCity = appDatabase.chosenCityDAO().get(filteredCity.getPlaceId()).blockingGet();
        appDatabase.chosenCityDAO().deleteCity(filteredCity.getPlaceId());
        appDatabase.fullWeatherReportDAO().deleteCity(storedCity.getLat(), storedCity.getLng());
    }
}
