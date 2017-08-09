package com.gcteam.yamblz.homework.domain.interactor;

import com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete.CitiesResponse;
import com.gcteam.yamblz.homework.data.repository.cities.CityRepository;
import com.gcteam.yamblz.homework.domain.interactor.cities.CityFilterInteractor;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;
import com.google.gson.Gson;

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
    private CityRepository cityRepository;
    private CitiesResponse citiesResponse;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TestScheduler executionScheduler = new TestScheduler();
        TestScheduler postExecutionScheduler = new TestScheduler();
        cityFilterInteractor = new CityFilterInteractor(
                cityRepository,
                executionScheduler,
                postExecutionScheduler
        );
        citiesResponse = new Gson().fromJson("{\"predictions\":[{\"description\":\"Dubai - United Arab Emirates\",\"id\":\"4517501182432a6539985e4dbd00278179f70a98\",\"matched_substrings\":[{\"length\":1,\"offset\":0}],\"place_id\":\"ChIJRcbZaklDXz4RYlEphFBu5r0\",\"reference\":\"CkQ0AAAAbaWhgwtQs3yJTPDKyaIGNFYLRjxgfq9fB9ZhSv2nXU4W5_7VTl1ECjDtCTD6W3jG38oeqMgYDbf7FeI7pKEnTxIQbA20pimrqs-TpR5z-Z9oEhoUwB_uWED9LunNMWoPOqQQhyBTjPk\",\"structured_formatting\":{\"main_text\":\"Dubai\",\"main_text_matched_substrings\":[{\"length\":1,\"offset\":0}],\"secondary_text\":\"United Arab Emirates\"},\"terms\":[{\"offset\":0,\"value\":\"Dubai\"},{\"offset\":8,\"value\":\"United Arab Emirates\"}],\"types\":[\"locality\",\"political\",\"geocode\"]},{\"description\":\"Delhi, India\",\"id\":\"9e280bae198a170435c5dff3faa5ef5e29328bc8\",\"matched_substrings\":[{\"length\":1,\"offset\":0}],\"place_id\":\"ChIJL_P_CXMEDTkRw0ZdG-0GVvw\",\"reference\":\"CjQkAAAAf2i07o3rn67-p5EsC9eTvcW1vtqTDvvEWklxwUNMH8p0ReVfMN2_gIwOxDWsMDD-EhBsS-B7jueA2U08GfTV044nGhSqyirYriivV1eYdzikS-IltoFkRA\",\"structured_formatting\":{\"main_text\":\"Delhi\",\"main_text_matched_substrings\":[{\"length\":1,\"offset\":0}],\"secondary_text\":\"India\"},\"terms\":[{\"offset\":0,\"value\":\"Delhi\"},{\"offset\":7,\"value\":\"India\"}],\"types\":[\"locality\",\"political\",\"geocode\"]},{\"description\":\"Дейра - Дубай - Объединенные Арабские Эмираты\",\"id\":\"5e76e43a54ff2f0380504fa8ed1b9d02b07a3c9e\",\"matched_substrings\":[{\"length\":5,\"offset\":0}],\"place_id\":\"ChIJk67NN09DXz4RkYS3oWNjdd4\",\"reference\":\"CnRqAAAAHpobAM_2Ls9Hx6CtA1Yt-yHNHdGCuNowpnaSRLjRjYqiCtJUnkpSOcbwydhap3dBxYvqwKWImPArF3u6Okbw3wbe4MPqa3t1nb5P4exFS8vDE3lymU510DvYTJGArXRO29qb3i2UtB9vALZ8JcwkURIQxUm8EMPyp5a0yMbqrPGkkxoUA7VzQHvwhaUMvQvACfOJ_RyuGFU\",\"structured_formatting\":{\"main_text\":\"Дейра\",\"main_text_matched_substrings\":[{\"length\":5,\"offset\":0}],\"secondary_text\":\"Дубай - Объединенные Арабские Эмираты\"},\"terms\":[{\"offset\":0,\"value\":\"Дейра\"},{\"offset\":8,\"value\":\"Дубай\"},{\"offset\":16,\"value\":\"Объединенные Арабские Эмираты\"}],\"types\":[\"sublocality_level_1\",\"sublocality\",\"political\",\"geocode\"]},{\"description\":\"Dallas, TX, United States\",\"id\":\"fa589a36153613fc17b0ebaebbea7c1e31ca62f0\",\"matched_substrings\":[{\"length\":1,\"offset\":0}],\"place_id\":\"ChIJS5dFe_cZTIYRj2dH9qSb7Lk\",\"reference\":\"CkQxAAAAxmaYA0r-vbD8ZBzXDDEv9KQYcYLl9GKHd482sL0DVWaUIqlDiVcA60DFM6t83Z4vrH4r5WGI6rO9-2uPSXKeQRIQidArXssHzbwUh9-GJM0VhBoUQv5uiDDP7vDVO2OgpPu-_Te7eiw\",\"structured_formatting\":{\"main_text\":\"Dallas\",\"main_text_matched_substrings\":[{\"length\":1,\"offset\":0}],\"secondary_text\":\"TX, United States\"},\"terms\":[{\"offset\":0,\"value\":\"Dallas\"},{\"offset\":8,\"value\":\"TX\"},{\"offset\":12,\"value\":\"United States\"}],\"types\":[\"locality\",\"political\",\"geocode\"]},{\"description\":\"Denver, CO, United States\",\"id\":\"464b513e4fd9a3c7c3bd63d091ecc856aff12e2c\",\"matched_substrings\":[{\"length\":1,\"offset\":0}],\"place_id\":\"ChIJzxcfI6qAa4cR1jaKJ_j0jhE\",\"reference\":\"CkQxAAAAn5IuBRj1llz1Vi84pgC4WKN5AB-XFUF2-tEw0gpEh6Wo8rveknO_zWuTn1iIyaWVekMWzAO6eV8lnQWXwsCS0RIQUhTOUzZ7mfPQJdPd8GtHDBoUOboKvpr5mJgFdeEFmoYRufURj5U\",\"structured_formatting\":{\"main_text\":\"Denver\",\"main_text_matched_substrings\":[{\"length\":1,\"offset\":0}],\"secondary_text\":\"CO, United States\"},\"terms\":[{\"offset\":0,\"value\":\"Denver\"},{\"offset\":8,\"value\":\"CO\"},{\"offset\":12,\"value\":\"United States\"}],\"types\":[\"locality\",\"political\",\"geocode\"]}],\"status\":\"OK\"}", CitiesResponse.class);
    }

    @Test
    public void getsCitiesFromRepositoryByInput() {
        List<FilteredCity> filteredCities = new ArrayList<>();
        filteredCities.add(new FilteredCity("city", "country", "123", 1L));
        when(cityRepository.getCitiesByFilter("input")).thenReturn(Single.just(filteredCities));
        cityFilterInteractor.getCitiesByFilter("input");
        verify(cityRepository).getCitiesByFilter("input");
    }

}
