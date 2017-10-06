package com.gcteam.yamblz.homework.data.mapper;

import android.support.annotation.NonNull;

import com.gcteam.yamblz.homework.data.api.dto.cities.details.CityDetailsResponse;
import com.gcteam.yamblz.homework.data.object.StoredCity;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

/**
 * Created by Kim Michael on 11.08.17
 */
public class StoredCityMapper {

    public static StoredCity toStoredCity(@NonNull CityDetailsResponse cityDetailsResponse, @NonNull FilteredCity filteredCity) throws Exception {
        return new StoredCity(
                cityDetailsResponse.getResult().getName(),
                cityDetailsResponse.getResult().getName(),
                filteredCity.getPriority(),
                cityDetailsResponse.getResult().getGeometry().getLocation().getLat(),
                cityDetailsResponse.getResult().getGeometry().getLocation().getLng(),
                filteredCity.getPlaceId(),
                filteredCity.getCountryName());
    }

    public static FilteredCity fromStoredCity(@NonNull StoredCity storedCity) {
        return new FilteredCity(
                storedCity.getCityName(),
                storedCity.getCountryName(),
                storedCity.getPlaceId(),
                storedCity.getPriority());
    }
}
