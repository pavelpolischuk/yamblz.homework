package com.gcteam.yamblz.homework.data.network.cities;

import com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete.CitiesResponse;
import com.gcteam.yamblz.homework.data.api.dto.cities.details.CityDetailsResponse;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;

import io.reactivex.Single;

/**
 * Created by Kim Michael on 13.08.17
 */
public interface CityService {

    Single<CitiesResponse> getSuggestionsByInput(String input);

    Single<CityDetailsResponse> getCityDetails(FilteredCity chosenCity);
}
