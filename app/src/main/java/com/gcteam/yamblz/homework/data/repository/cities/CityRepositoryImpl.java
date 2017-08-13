package com.gcteam.yamblz.homework.data.repository.cities;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.gcteam.yamblz.homework.data.local.cities.CityStorage;
import com.gcteam.yamblz.homework.data.mapper.CitiesResponseMapper;
import com.gcteam.yamblz.homework.data.mapper.StoredCityMapper;
import com.gcteam.yamblz.homework.data.network.cities.CityService;
import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by Kim Michael on 03.08.17
 */
public class CityRepositoryImpl implements CityRepository {

    private final CityStorage roomCityStorage;
    private final CityService cityService;
    private final PreferencesManager preferencesManager;

    public CityRepositoryImpl(CityStorage roomCityStorage,
                              CityService cityService,
                              PreferencesManager preferencesManager) {
        this.roomCityStorage = roomCityStorage;
        this.cityService = cityService;
        this.preferencesManager = preferencesManager;
    }

    @NonNull
    public Single<List<FilteredCity>> getCitiesByFilter(@NonNull String input) {
        Timber.d("Getting cities by input : %s", input);
        return cityService.getSuggestionsByInput(input)
                .map(CitiesResponseMapper::toFilteredCities);
    }

    @WorkerThread
    @NonNull
    public Single<StoredCity> saveChosenCityDetails(FilteredCity filteredCity) {
        Timber.d("Getting city details for city : %s", filteredCity.getCityName());
        Single<StoredCity> storageData = roomCityStorage.getChosenCity(filteredCity)
                .map(storedCity -> {
                    storedCity.setPriority(filteredCity.getPriority());
                    return storedCity;
                });
        Single<StoredCity> serviceData = cityService.getCityDetails(filteredCity)
                .map(cityDetailsResponse ->
                        StoredCityMapper.toStoredCity(cityDetailsResponse, filteredCity));
        return storageData
                .onErrorResumeNext(serviceData)
                .doOnSuccess(this::saveChosenCity);
    }

    @WorkerThread
    public void saveChosenCityDetails(StoredCity storedCity) {
        Timber.d(storedCity.getCityName() + " city details are saved");
        roomCityStorage.saveCityDetails(storedCity);
    }

    @WorkerThread
    @NonNull
    public Single<List<FilteredCity>> getCities() {
        Timber.d("Getting all chosen cities");
        return roomCityStorage.getChosenCities()
                .flatMap(storedChosenCities -> Observable.fromIterable(storedChosenCities)
                        .map(StoredCityMapper::fromStoredCity)
                        .toList());
    }

    @WorkerThread
    public void saveChosenCity(StoredCity storedCity) {
        Timber.d(storedCity.getCityName() + " is saved to SharedPreferences");
        preferencesManager.saveChosenCity(storedCity);
    }

    @WorkerThread
    public void deleteCity(FilteredCity filteredCity) {
        Timber.d(filteredCity.getCityName() + " is deleted");
        roomCityStorage.deleteCity(filteredCity);
    }

    @WorkerThread
    public void setNoChosenCity() {
        Timber.d("No city chosen");
        preferencesManager.saveNoChosenCity();
    }
}
