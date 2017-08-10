package com.gcteam.yamblz.homework.data.repository.cities;

import android.support.annotation.NonNull;

import com.gcteam.yamblz.homework.data.CitiesResponseMapper;
import com.gcteam.yamblz.homework.data.local.cities.CityStorage;
import com.gcteam.yamblz.homework.data.network.cities.CityService;
import com.gcteam.yamblz.homework.data.object.StoredChosenCity;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import timber.log.Timber;

/**
 * Created by Kim Michael on 03.08.17
 */
public class CityRepository {

    private final CityStorage cityStorage;
    private final CityService cityService;
    private final PreferencesManager preferencesManager;
    private final CitiesResponseMapper citiesResponseMapper;

    public CityRepository(CityStorage cityStorage,
                          CityService cityService,
                          PreferencesManager preferencesManager,
                          CitiesResponseMapper citiesResponseMapper) {
        this.cityStorage = cityStorage;
        this.cityService = cityService;
        this.preferencesManager = preferencesManager;
        this.citiesResponseMapper = citiesResponseMapper;
    }

    @NonNull
    public Single<List<FilteredCity>> getCitiesByFilter(@NonNull String input) {
        return cityService.getSuggestionsByInput(input)
                .map(citiesResponseMapper);
    }

    public Single<StoredChosenCity> getCityDetails(FilteredCity chosenCity) {
        return cityStorage.getChosenCity(chosenCity).onErrorResumeNext(
                cityService.getCityDetails(chosenCity)
                .map(cityDetailsResponse -> new StoredChosenCity(
                        cityDetailsResponse.getResult().getName(),
                        cityDetailsResponse.getResult().getName(),
                        chosenCity.getId(),
                        cityDetailsResponse.getResult().getGeometry().getLocation().getLat(),
                        cityDetailsResponse.getResult().getGeometry().getLocation().getLng(),
                        chosenCity.getPlaceId(),
                        chosenCity.getCountryName())))
                .doOnSuccess(this::saveChosenCity);
    }

    public void saveCityDetails(StoredChosenCity storedChosenCity) {
        Timber.d(storedChosenCity.getCityName() + " city details are saved");
        cityStorage.saveCityDetails(storedChosenCity);
    }

    public Single<List<FilteredCity>> getCities() {
        return cityStorage.getChosenCities()
                .flatMap(storedChosenCities -> Observable.fromIterable(storedChosenCities)
                        .map(storedChosenCity -> new FilteredCity(
                                storedChosenCity.getCityName(),
                                storedChosenCity.getCountryName(),
                                storedChosenCity.getPlaceId(),
                                storedChosenCity.getPriority())).toList());
    }

    public void saveChosenCity(StoredChosenCity storedChosenCity) {
        Timber.d(storedChosenCity.getCityName() + " is saved to SharedPreferences");
        preferencesManager.saveChosenCity(storedChosenCity);
    }

    public void deleteCity(FilteredCity filteredCity) {
        Timber.d(filteredCity.getCityName() + " is deleted");
        cityStorage.deleteCity(filteredCity);
    }

    public void setNoChosenCity() {
        Timber.d("No city chosen");
        preferencesManager.saveNoChosenCity();
    }
}
