package com.gcteam.yamblz.homework.domain.interactor.cities;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.data.repository.cities.CityRepository;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import timber.log.Timber;

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

    @NonNull
    @MainThread
    public Single<StoredCity> addCity(@NonNull FilteredCity chosenCity) {
        Timber.d("Getting city details and saving to databse for city : %s", chosenCity.getCityName() );
        return cityRepository.getCityDetails(chosenCity)
                .doOnSuccess(cityRepository::saveCityDetails)
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }

    @NonNull
    @WorkerThread
    public Single<List<FilteredCity>> getChosenCities() {
        Timber.d("Getting chosen cities");
        return cityRepository.getCities()
                .map(filteredCities -> {
                    Collections.reverse(filteredCities);
                    return filteredCities;
                })
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }

    @NonNull
    @WorkerThread
    public Single<StoredCity> chooseCity(@NonNull FilteredCity filteredCity) {
        Timber.d("Setting chosen city : %s", filteredCity.getCityName());
        return cityRepository.getCityDetails(filteredCity)
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }

    @WorkerThread
    public void deleteCity(@NonNull FilteredCity filteredCity) {
        Timber.d("Deleting city : %s", filteredCity.getCityName());
        executorService.execute(() -> cityRepository.deleteCity(filteredCity));
    }

    @WorkerThread
    public void setNoChosenCity() {
        Timber.d("Setting no chosen city");
        cityRepository.setNoChosenCity();
    }
}
