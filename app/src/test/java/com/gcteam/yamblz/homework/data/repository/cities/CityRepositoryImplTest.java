package com.gcteam.yamblz.homework.data.repository.cities;

import com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete.CitiesResponse;
import com.gcteam.yamblz.homework.data.api.dto.cities.details.CityDetailsResponse;
import com.gcteam.yamblz.homework.data.local.cities.CityStorage;
import com.gcteam.yamblz.homework.data.mapper.CitiesResponseMapper;
import com.gcteam.yamblz.homework.data.mapper.StoredCityMapper;
import com.gcteam.yamblz.homework.data.network.cities.CityService;
import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Kim Michael on 11.08.17
 */
public class CityRepositoryImplTest {

    private static final String INPUT = "input";

    CityRepositoryImpl cityRepositoryImpl;
    @Mock
    CityStorage cityStorage;
    @Mock
    CityService cityService;
    @Mock
    PreferencesManager preferencesManager;

    CitiesResponse citiesResponse;
    FilteredCity filteredCity;
    StoredCity storedCity;
    StoredCity mappedStoredCity;
    CityDetailsResponse cityDetailsResponse;

    CitiesResponseMapper citiesResponseMapper;
    StoredCityMapper storedCityMapper;

    List<StoredCity> chosenCities;


    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        citiesResponseMapper = new CitiesResponseMapper();
        storedCityMapper = new StoredCityMapper();
        cityRepositoryImpl = new CityRepositoryImpl(
                cityStorage,
                cityService,
                preferencesManager
        );
        citiesResponse = random(CitiesResponse.class);
        filteredCity = random(FilteredCity.class);
        storedCity = random(StoredCity.class);
        cityDetailsResponse = random(CityDetailsResponse.class);
        mappedStoredCity = StoredCityMapper.toStoredCity(cityDetailsResponse, filteredCity);
        chosenCities = new ArrayList<>();
        chosenCities.add(storedCity);
    }

    @Test
    public void getsCitiesByFilterFromService() {
        when(cityService.getSuggestionsByInput(INPUT)).thenReturn(Single.just(citiesResponse));

        cityRepositoryImpl.getCitiesByFilter(INPUT);

        verify(cityService, times(1)).getSuggestionsByInput(INPUT);
    }

    @Test
    public void getCityDetailsFromRepository() {
        when(cityStorage.getChosenCity(filteredCity)).thenReturn(Single.just(storedCity));
        when(cityService.getCityDetails(filteredCity)).thenReturn(Single.just(cityDetailsResponse));

        StoredCity fetchedCity = cityRepositoryImpl.saveChosenCityDetails(filteredCity).blockingGet();

        assertThat(fetchedCity).isEqualTo(storedCity);
        assertThat(fetchedCity).isNotEqualTo(mappedStoredCity);
    }

    @Test
    public void getAllChosenCitiesFromStorage() {
        when(cityStorage.getChosenCities()).thenReturn(Single.just(chosenCities));
        List<FilteredCity> fetchedChosenCities = cityRepositoryImpl.getCities().blockingGet();
        assertThat(fetchedChosenCities).containsOnly(StoredCityMapper.fromStoredCity(storedCity));
    }

    @Test
    public void ifStorageReturnsError_getCityDetailsFromService() {
        when(cityStorage.getChosenCity(filteredCity)).thenReturn(Single.error(new Exception("Error")));
        when(cityService.getCityDetails(filteredCity)).thenReturn(Single.just(cityDetailsResponse));

        StoredCity fetchedCity = cityRepositoryImpl.saveChosenCityDetails(filteredCity).blockingGet();

        assertThat(fetchedCity).isEqualTo(mappedStoredCity);
    }

    @Test
    public void savesCityDetails_delegatesToStorage() {
        cityRepositoryImpl.saveChosenCityDetails(storedCity);
        verify(cityStorage, times(1)).saveCityDetails(storedCity);
    }

    @Test
    public void setNoChosenCity_delegatesToPreferencesManager() {
        cityRepositoryImpl.setNoChosenCity();
        verify(preferencesManager, times(1)).saveNoChosenCity();
    }

    @Test
    public void setChosenCity_delegatesToPreferencesManager() {
        cityRepositoryImpl.saveChosenCity(storedCity);
        verify(preferencesManager, times(1)).saveChosenCity(storedCity);
    }

    @Test
    public void deleteCity_delegatesToStorage() {
        cityRepositoryImpl.deleteCity(filteredCity);
        verify(cityStorage, times(1)).deleteCity(filteredCity);
    }

}
