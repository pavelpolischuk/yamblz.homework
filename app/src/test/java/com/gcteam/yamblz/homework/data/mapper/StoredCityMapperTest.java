package com.gcteam.yamblz.homework.data.mapper;

import com.gcteam.yamblz.homework.data.api.dto.cities.details.CityDetailsResponse;
import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import org.junit.Test;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Kim Michael on 11.08.17
 */
public class StoredCityMapperTest {

    @Test
    public void mapsCityDetailsResponseAndFilteredCity_toStoredCity() throws Exception {
        CityDetailsResponse cityDetailsResponse = random(CityDetailsResponse.class);
        FilteredCity filteredCity = random(FilteredCity.class);
        StoredCity storedCity = StoredCityMapper.toStoredCity(cityDetailsResponse, filteredCity);
        assertThat(storedCity.getCityName())
                .isEqualTo(cityDetailsResponse.getResult().getName());
        assertThat(storedCity.getPlaceId())
                .isEqualTo(filteredCity.getPlaceId());
        assertThat(storedCity.getCountryName())
                .isEqualTo(filteredCity.getCountryName());
        assertThat(storedCity.getLat())
                .isEqualTo(cityDetailsResponse.getResult().getGeometry().getLocation().getLat());
        assertThat(storedCity.getLng())
                .isEqualTo(cityDetailsResponse.getResult().getGeometry().getLocation().getLng());
        assertThat(storedCity.getUserCityName())
                .isEqualTo(cityDetailsResponse.getResult().getName());
    }
}
