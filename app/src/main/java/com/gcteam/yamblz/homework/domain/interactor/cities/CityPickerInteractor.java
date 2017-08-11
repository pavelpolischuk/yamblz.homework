package com.gcteam.yamblz.homework.domain.interactor.cities;

import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.data.repository.cities.CityRepository;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Created by Kim Michael on 03.08.17
 */
public class CityPickerInteractor {

    private final CityRepository cityRepository;
    private final Scheduler executionScheduler;
    private final Scheduler postExecutionScheduler;
    private final ExecutorService executorService;

    @Inject
    public CityPickerInteractor(CityRepository cityRepository,
                                Scheduler executionScheduler,
                                Scheduler postExecutionScheduler,
                                ExecutorService executorService) {
        this.cityRepository = cityRepository;
        this.executionScheduler = executionScheduler;
        this.postExecutionScheduler = postExecutionScheduler;
        this.executorService = executorService;
    }


    public Single<StoredCity> addCity(FilteredCity chosenCity) {
        return cityRepository.getCityDetails(chosenCity)
                .doOnSuccess(cityRepository::saveCityDetails)
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }

    public Single<List<FilteredCity>> getChosenCities() {
        return cityRepository.getCities()
                .map(filteredCities -> {
                    Collections.reverse(filteredCities);
                    return filteredCities;
                })
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }

    public Single<StoredCity> chooseCity(FilteredCity filteredCity) {
        return cityRepository.getCityDetails(filteredCity)
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }

    public void deleteCity(FilteredCity filteredCity) {
        executorService.execute(() -> cityRepository.deleteCity(filteredCity));
    }

    public void setNoChosenCity() {
        cityRepository.setNoChosenCity();
    }
}
