package com.gcteam.yamblz.homework.weather;

import com.gcteam.yamblz.homework.data.WeatherResponseMapper;
import com.gcteam.yamblz.homework.data.api.dto.weather.current.WeatherResponse;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kim Michael on 27.07.17
 */
public class WeatherMapperTest {

    WeatherResponseMapper weatherResponseMapper;
    WeatherResponse weatherResponse;
    Gson gson;

    @Before
    public void setup() {
        gson = new Gson();
        weatherResponse = gson.fromJson("{\"coord\":{\"lon\":30.52,\"lat\":50.45},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":24.34,\"pressure\":1004,\"humidity\":78,\"temp_min\":23,\"temp_max\":26},\"visibility\":10000,\"wind\":{\"speed\":6,\"deg\":360},\"clouds\":{\"all\":75},\"dt\":1501250400,\"sys\":{\"type\":1,\"id\":7358,\"message\":0.0041,\"country\":\"UA\",\"sunrise\":1501208450,\"sunset\":1501264023},\"id\":696050,\"name\":\"Pushcha-Voditsa\",\"cod\":200}", WeatherResponse.class);
        weatherResponseMapper = new WeatherResponseMapper();
    }

    @Test
    public void mapsWeatherToWeatherData() throws Exception {
        WeatherData weatherData = weatherResponseMapper.apply(weatherResponse);
        assertEquals("broken clouds", weatherData.getDescription());
        assertEquals(78, weatherData.getHumidity());
        assertEquals(1004.0, weatherData.getPressure(), 1.0);
        assertEquals(24.34f, weatherData.getDayTemp(), 1.0);
        assertEquals(6.0f, weatherData.getWindSpeed(), 1.0);
        assertEquals(360.0d, weatherData.getWindDeg(), 1.0);
        assertEquals(803, weatherData.getWeatherId());

    }
}
