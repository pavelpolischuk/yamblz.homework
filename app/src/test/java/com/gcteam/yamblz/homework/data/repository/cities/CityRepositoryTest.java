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

import io.reactivex.Single;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Kim Michael on 11.08.17
 */
public class CityRepositoryTest {

    private static final String INPUT = "input";

    CityRepository cityRepository;
    @Mock
    CityStorage cityStorage;
    @Mock
    CityService cityService;
    @Mock
    PreferencesManager preferencesManager;

    CitiesResponse citiesResponse;
    FilteredCity filteredCity;
    StoredCity storedCity;
    CityDetailsResponse cityDetailsResponse;

    CitiesResponseMapper citiesResponseMapper;
    StoredCityMapper storedCityMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        citiesResponseMapper = new CitiesResponseMapper();
        storedCityMapper = new StoredCityMapper();
        cityRepository = new CityRepository(
                cityStorage,
                cityService,
                preferencesManager,
                citiesResponseMapper,
                storedCityMapper
        );
        citiesResponse = random(CitiesResponse.class);
        filteredCity = random(FilteredCity.class);
        storedCity = random(StoredCity.class);
        cityDetailsResponse = random(CityDetailsResponse.class);
    }

    @Test
    public void getsCitiesByFilterFromService() {
        when(cityService.getSuggestionsByInput(INPUT)).thenReturn(Single.just(citiesResponse));
        cityRepository.getCitiesByFilter(INPUT);
        verify(cityService).getSuggestionsByInput(INPUT);
    }

}
