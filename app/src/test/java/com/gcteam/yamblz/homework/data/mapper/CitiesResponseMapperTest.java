package com.gcteam.yamblz.homework.data.mapper;

import com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete.CitiesResponse;
import com.gcteam.yamblz.homework.data.api.dto.cities.autocomplete.Prediction;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Kim Michael on 11.08.17
 */
public class CitiesResponseMapperTest {

    Gson gson;
    String citiesResponseJson = "{ \"predictions\" : [ { \"description\" : \"город Киев, Украина\", \"id\" : \"ccb2dc1226ba7f7705a48dc9e1946615386fac5d\", \"matched_substrings\" : [ { \"length\" : 1, \"offset\" : 0 } ], \"place_id\" : \"ChIJBUVa4U7P1EARBBiQlxMdJV8\", \"reference\" : \"CkQ7AAAAK4FdffRK3W5CALicEkycRjfhQb8Ec2dg624rcOoybIPDK87SGOex2bcPMz6Wdf5YWQzzJsOVD9xbA7eZWhDoWRIQnAgy-ufKu4O-hVmiJFYfhRoUTRcoJOdWWgstjnscjrVDk7d-5eA\", \"structured_formatting\" : { \"main_text\" : \"город Киев\", \"main_text_matched_substrings\" : [ { \"length\" : 1, \"offset\" : 0 } ], \"secondary_text\" : \"Украина\" }, \"terms\" : [ { \"offset\" : 0, \"value\" : \"город Киев\" }, { \"offset\" : 12, \"value\" : \"Украина\" } ], \"types\" : [ \"administrative_area_level_1\", \"political\", \"geocode\" ] }, { \"description\" : \"Германия\", \"id\" : \"7f82b6151862823bfd55dc893a02c7db84616db0\", \"matched_substrings\" : [ { \"length\" : 1, \"offset\" : 0 } ], \"place_id\" : \"ChIJa76xwh5ymkcRW-WRjmtd6HU\", \"reference\" : \"CjQoAAAA4DQ8kJAYxi_PzrrenTstUJclxlFnA8b0etAjEJ1kuWnlmDSnd8DXy_8xPQkVYdmpEhAZTWUUttZgePpZc4qC9-DFGhSZnQyWVh57tZSz3Sr4mv-3badq4w\", \"structured_formatting\" : { \"main_text\" : \"Германия\", \"main_text_matched_substrings\" : [ { \"length\" : 1, \"offset\" : 0 } ] }, \"terms\" : [ { \"offset\" : 0, \"value\" : \"Германия\" } ], \"types\" : [ \"country\", \"political\", \"geocode\" ] }, { \"description\" : \"Греция\", \"id\" : \"1fdf50bcc54a4d3bd07b779713363b0fda5b6cfb\", \"matched_substrings\" : [ { \"length\" : 1, \"offset\" : 0 } ], \"place_id\" : \"ChIJY2xxEcdKWxMRHS2a3HUXOjY\", \"reference\" : \"CjQkAAAADZvHvnUI1g5KE1EAo0bsq7Xyy1_oJGrzEFJjLsgsk7ClDj0etkM38J-mr0vvTYkmEhDY5biuT8sGUX7F8FUViyoqGhRNupVrw44KEzJdgQC8Eg1Dnptkpg\", \"structured_formatting\" : { \"main_text\" : \"Греция\", \"main_text_matched_substrings\" : [ { \"length\" : 1, \"offset\" : 0 } ] }, \"terms\" : [ { \"offset\" : 0, \"value\" : \"Греция\" } ], \"types\" : [ \"country\", \"political\", \"geocode\" ] }, { \"description\" : \"Грузия\", \"id\" : \"6e4dc2877fdb95c0e3d0d36dfa987bd8216c6ac2\", \"matched_substrings\" : [ { \"length\" : 1, \"offset\" : 0 } ], \"place_id\" : \"ChIJa2JP5tcMREARwkotEmR5kE8\", \"reference\" : \"CjQkAAAA2Z7NMcvekUV3NdpgprmXbteIXh5infDXfR9DkDtcivyAduA9HqVEErpuCK9saoVYEhAjnX-D3wwfJzVgm5yndISmGhRCOxeXs0lXrpB25M2BX384-Gn9Ag\", \"structured_formatting\" : { \"main_text\" : \"Грузия\", \"main_text_matched_substrings\" : [ { \"length\" : 1, \"offset\" : 0 } ] }, \"terms\" : [ { \"offset\" : 0, \"value\" : \"Грузия\" } ], \"types\" : [ \"country\", \"political\", \"geocode\" ] }, { \"description\" : \"Геленджик, Краснодарский край, Россия\", \"id\" : \"07c1b90aeec8040f5833c4903dd14afcfad13ec2\", \"matched_substrings\" : [ { \"length\" : 1, \"offset\" : 0 } ], \"place_id\" : \"ChIJrbl_Lk6P8UARFjCi8rPAe5w\", \"reference\" : \"CmRdAAAA99BuRmoYcz1uo1TGmBOOizobqj-pdDwpI1W7OUgYxNsC-tOx5iocXH21eYlDPuGrE-oDiMJPOckzhCZBB7RIIIeCT2fpKYFDc0bHzqFXdg9zJ7GbSuE0LW3xXzLigGByEhBIqSgZhwM_fhD4IKPNSE4cGhQ6E7b5YgH5UNdPvUA857udIJx8oQ\", \"structured_formatting\" : { \"main_text\" : \"Геленджик\", \"main_text_matched_substrings\" : [ { \"length\" : 1, \"offset\" : 0 } ], \"secondary_text\" : \"Краснодарский край, Россия\" }, \"terms\" : [ { \"offset\" : 0, \"value\" : \"Геленджик\" }, { \"offset\" : 11, \"value\" : \"Краснодарский край\" }, { \"offset\" : 31, \"value\" : \"Россия\" } ], \"types\" : [ \"locality\", \"political\", \"geocode\" ] } ], \"status\" : \"OK\" }";
    CitiesResponse citiesResponse;

    @Before
    public void setup() {
        gson = new Gson();
        citiesResponse = gson.fromJson(citiesResponseJson, CitiesResponse.class);
    }

    @Test
    public void mapsCitiesResponse_toListOfFilteredCities() throws Exception {
        List<FilteredCity> filteredCities = CitiesResponseMapper.toFilteredCities(citiesResponse);
        for (int i = 0; i < filteredCities.size(); i++) {
            FilteredCity filteredCity = filteredCities.get(i);
            Prediction prediction = citiesResponse.getPredictions().get(i);

            assertThat(filteredCity.getCityName())
                    .isEqualTo(prediction.getStructuredFormatting().getMainText());
            assertThat(filteredCity.getCountryName())
                    .isEqualTo(prediction.getStructuredFormatting().getSecondaryText());
            assertThat(filteredCity.getPlaceId())
                    .isEqualTo(prediction.getPlaceId());

        }
    }
}
