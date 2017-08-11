package com.gcteam.yamblz.homework.data.mapper;

import com.gcteam.yamblz.homework.data.api.dto.cities.details.CityDetailsResponse;
import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;

/**
 * Created by Kim Michael on 11.08.17
 */
public class StoredCityMapper implements BiFunction<CityDetailsResponse, FilteredCity, StoredCity> {

    @Override
    public StoredCity apply(@NonNull CityDetailsResponse cityDetailsResponse, @NonNull FilteredCity filteredCity) throws Exception {
        return new StoredCity(
                cityDetailsResponse.getResult().getName(),
                cityDetailsResponse.getResult().getName(),
                filteredCity.getId(),
                cityDetailsResponse.getResult().getGeometry().getLocation().getLat(),
                cityDetailsResponse.getResult().getGeometry().getLocation().getLng(),
                filteredCity.getPlaceId(),
                filteredCity.getCountryName());
    }
}
