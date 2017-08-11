package com.gcteam.yamblz.homework.data.repository.cities;

import android.support.annotation.NonNull;

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
public class CityRepository {

    private final CityStorage cityStorage;
    private final CityService cityService;
    private final PreferencesManager preferencesManager;
    private final CitiesResponseMapper citiesResponseMapper;
    private final StoredCityMapper storedCityMapper;

    public CityRepository(CityStorage cityStorage,
                          CityService cityService,
                          PreferencesManager preferencesManager,
                          CitiesResponseMapper citiesResponseMapper,
                          StoredCityMapper storedCityMapper) {
        this.cityStorage = cityStorage;
        this.cityService = cityService;
        this.preferencesManager = preferencesManager;
        this.citiesResponseMapper = citiesResponseMapper;
        this.storedCityMapper = storedCityMapper;
    }

    @NonNull
    public Single<List<FilteredCity>> getCitiesByFilter(@NonNull String input) {
        return cityService.getSuggestionsByInput(input)
                .map(citiesResponseMapper);
    }

    public Single<StoredCity> getCityDetails(FilteredCity filteredCity) {
        return cityStorage.getChosenCity(filteredCity)
                .onErrorResumeNext(
                    cityService.getCityDetails(filteredCity)
                    .map(cityDetailsResponse ->
                            storedCityMapper.apply(cityDetailsResponse, filteredCity))
                )
                .doOnSuccess(this::saveChosenCity);
    }

    public void saveCityDetails(StoredCity storedCity) {
        Timber.d(storedCity.getCityName() + " city details are saved");
        cityStorage.saveCityDetails(storedCity);
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

    public void saveChosenCity(StoredCity storedCity) {
        Timber.d(storedCity.getCityName() + " is saved to SharedPreferences");
        preferencesManager.saveChosenCity(storedCity);
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
