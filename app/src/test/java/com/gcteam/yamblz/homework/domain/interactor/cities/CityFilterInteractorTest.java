package com.gcteam.yamblz.homework.domain.interactor.cities;

import com.gcteam.yamblz.homework.data.repository.cities.CityRepositoryImpl;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Kim Michael on 04.08.17
 */
public class CityFilterInteractorTest {

    private CityFilterInteractor cityFilterInteractor;
    @Mock
    private CityRepositoryImpl cityRepositoryImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TestScheduler executionScheduler = new TestScheduler();
        TestScheduler postExecutionScheduler = new TestScheduler();
        cityFilterInteractor = new CityFilterInteractor(
                cityRepositoryImpl,
                executionScheduler,
                postExecutionScheduler
        );
    }

    @Test
    public void getsCitiesFromRepositoryByInput() {
        List<FilteredCity> filteredCities = new ArrayList<>();
        filteredCities.add(new FilteredCity("city", "country", "123", 1));
        when(cityRepositoryImpl.getCitiesByFilter("input")).thenReturn(Single.just(filteredCities));
        cityFilterInteractor.getCitiesByFilter("input");
        verify(cityRepositoryImpl).getCitiesByFilter("input");
    }

}
