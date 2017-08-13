package com.gcteam.yamblz.homework.data.mapper;

import com.gcteam.yamblz.homework.data.api.dto.weather.forecast.Description;
import com.gcteam.yamblz.homework.data.api.dto.weather.forecast.ForecastResponse;
import com.gcteam.yamblz.homework.domain.object.ForecastData;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Kim Michael on 11.08.17
 */
public class ForecastResponseMapperTest {

    Gson gson;
    ForecastResponse forecastResponse;
    String forecastJson = "{\"city\":{\"id\":558418,\"name\":\"Groznyy\",\"coord\":{\"lon\":45.7003,\"lat\":43.3083},\"country\":\"RU\",\"population\":226100},\"cod\":\"200\",\"message\":2.524514,\"cnt\":7,\"list\":[{\"dt\":1502442000,\"temp\":{\"day\":29.02,\"min\":20.19,\"max\":29.02,\"night\":20.19,\"eve\":27.04,\"morn\":29.02},\"pressure\":983.25,\"humidity\":49,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"ясно\",\"icon\":\"01d\"}],\"speed\":5.2,\"deg\":75,\"clouds\":0},{\"dt\":1502528400,\"temp\":{\"day\":28.86,\"min\":18.64,\"max\":30.38,\"night\":20.44,\"eve\":28.32,\"morn\":18.64},\"pressure\":980.46,\"humidity\":56,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"ясно\",\"icon\":\"01d\"}],\"speed\":2.32,\"deg\":64,\"clouds\":0},{\"dt\":1502614800,\"temp\":{\"day\":29.75,\"min\":18.79,\"max\":31.47,\"night\":23.37,\"eve\":29.8,\"morn\":18.79},\"pressure\":978.06,\"humidity\":55,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"ясно\",\"icon\":\"01d\"}],\"speed\":2.68,\"deg\":61,\"clouds\":0},{\"dt\":1502701200,\"temp\":{\"day\":30.49,\"min\":20.7,\"max\":30.53,\"night\":23.39,\"eve\":28.55,\"morn\":20.7},\"pressure\":979.53,\"humidity\":57,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"легкий дождь\",\"icon\":\"10d\"}],\"speed\":2.91,\"deg\":50,\"clouds\":12,\"rain\":1.12},{\"dt\":1502787600,\"temp\":{\"day\":27.87,\"min\":15.99,\"max\":27.87,\"night\":15.99,\"eve\":20.04,\"morn\":23.69},\"pressure\":914.36,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"легкий дождь\",\"icon\":\"10d\"}],\"speed\":3.01,\"deg\":86,\"clouds\":6,\"rain\":0.58},{\"dt\":1502874000,\"temp\":{\"day\":24.7,\"min\":15.07,\"max\":24.7,\"night\":15.07,\"eve\":18,\"morn\":19.63},\"pressure\":915.97,\"humidity\":0,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"дождь\",\"icon\":\"10d\"}],\"speed\":1.59,\"deg\":43,\"clouds\":7,\"rain\":4.33},{\"dt\":1502960400,\"temp\":{\"day\":24.31,\"min\":14.13,\"max\":24.31,\"night\":14.13,\"eve\":18.38,\"morn\":20.12},\"pressure\":917.4,\"humidity\":0,\"weather\":[{\"id\":501,\"main\":\"Rain\",\"description\":\"дождь\",\"icon\":\"10d\"}],\"speed\":2.33,\"deg\":70,\"clouds\":17,\"rain\":3.3}]}";

    @Before
    public void setup() {
        gson = new Gson();
        forecastResponse = gson.fromJson(forecastJson, ForecastResponse.class);
    }

    @Test
    public void mapsForecastResponse_toForecastData() throws Exception {
        ForecastData forecastData = ForecastResponseMapper.toForecastData(forecastResponse);
        assertThat(forecastData.getCityName()).isEqualToIgnoringCase(forecastResponse.getCity().getName());
        assertThat(forecastData.getForecast()).hasSameSizeAs(forecastResponse.getDescriptions());
        for (int i = 0; i < forecastData.getForecast().size(); i++) {
            WeatherData weatherData = forecastData.getForecast().get(i);
            Description description = forecastResponse.getDescriptions().get(i);
            assertThat(weatherData.getWeatherId()).isEqualTo(description.getWeather().get(0).getId());
            assertThat(weatherData.getDate()).isEqualTo(description.getDt());
            assertThat(weatherData.getHumidity()).isEqualTo(description.getHumidity());
            assertThat(weatherData.getDayTemp()).isEqualTo(description.getTemp().getDay());
            assertThat(weatherData.getMaxTemp()).isEqualTo(description.getTemp().getMax());
            assertThat(weatherData.getMinTemp()).isEqualTo(description.getTemp().getMin());
            assertThat(weatherData.getPressure()).isEqualTo(description.getPressure());
            assertThat(weatherData.getDescription())
                    .isEqualTo(description.getWeather().get(0).getDescription());
            assertThat(weatherData.getWindDeg()).isEqualTo(description.getDeg());
            assertThat(weatherData.getWindSpeed()).isEqualTo(description.getSpeed());
        }

    }
}
