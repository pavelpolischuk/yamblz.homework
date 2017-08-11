package com.gcteam.yamblz.homework.data.mapper;

import com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete.CitiesResponse;
import com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete.Prediction;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Kim Michael on 04.08.17
 */
public class CitiesResponseMapper implements Function<CitiesResponse, List<FilteredCity>> {
    @Override
    @NonNull
    public List<FilteredCity> apply(@NonNull CitiesResponse citiesResponse) throws Exception {
        List<FilteredCity> filteredCities = new ArrayList<>();
        FilteredCity filteredCity;
        for (Prediction prediction : citiesResponse.getPredictions()) {
            filteredCity = new FilteredCity(
                    prediction.getStructuredFormatting().getMainText(),
                    prediction.getStructuredFormatting().getSecondaryText(),
                    prediction.getPlaceId()
            );
            filteredCities.add(filteredCity);
        }
        return filteredCities;
    }
}
