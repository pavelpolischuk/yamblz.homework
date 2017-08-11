package com.gcteam.yamblz.homework.data.network.cities;

import com.gcteam.yamblz.homework.data.api.GooglePlacesAPI;
import com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete.CitiesResponse;
import com.gcteam.yamblz.homework.data.api.dto.cities.details.CityDetailsResponse;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Locale;

import io.reactivex.Single;

import static org.mockito.Mockito.when;

/**
 * Created by Kim Michael on 11.08.17
 */
public class CityServiceTest {

    private static final String PLACE_ID = "place_id";
    private static final String INPUT = "input";
    private static final String TYPES = "(cities)";

    CityService cityService;
    @Mock
    GooglePlacesAPI googlePlacesAPI;
    @Mock
    CitiesResponse citiesResponse;
    @Mock
    CityDetailsResponse cityDetailsResponse;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        cityService = new CityService(googlePlacesAPI);
        when(googlePlacesAPI
                .getCityDetails(GooglePlacesAPI.API_KEY,
                        PLACE_ID, Locale.getDefault().getLanguage()))
                .thenReturn(Single.just(cityDetailsResponse));
        when(googlePlacesAPI
                .getSuggestionsByInput(GooglePlacesAPI.API_KEY,
                        INPUT, TYPES, Locale.getDefault().getLanguage()))
        .thenReturn(Single.just(citiesResponse));
    }

    @Test
    public void getSuggestionByInput() {
        CitiesResponse citiesResponse = cityService
                .getSuggestionsByInput(INPUT)
                .blockingGet();
        Assertions.assertThat(citiesResponse).isSameAs(this.citiesResponse);
    }

    @Test
    public void getCityDetailsForFilteredCity() {
        FilteredCity filteredCity = new FilteredCity("city", "country", PLACE_ID);
        CityDetailsResponse cityDetailsResponse =
                cityService.getCityDetails(filteredCity)
                        .blockingGet();
        Assertions.assertThat(cityDetailsResponse).isSameAs(this.cityDetailsResponse);
    }
}
