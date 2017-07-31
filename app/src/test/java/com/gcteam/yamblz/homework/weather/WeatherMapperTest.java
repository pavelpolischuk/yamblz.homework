package com.gcteam.yamblz.homework.weather;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.data.WeatherData;
import com.gcteam.yamblz.homework.data.WeatherMapper;
import com.gcteam.yamblz.homework.data.api.dto.Weather;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Kim Michael on 27.07.17
 */
public class WeatherMapperTest {

    WeatherMapper weatherMapper;
    Weather weather;
    Gson gson;

    @Before
    public void setup() {
        gson = new Gson();
        weather = gson.fromJson("{\"coord\":{\"lon\":30.52,\"lat\":50.45},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":24.34,\"pressure\":1004,\"humidity\":78,\"temp_min\":23,\"temp_max\":26},\"visibility\":10000,\"wind\":{\"speed\":6,\"deg\":360},\"clouds\":{\"all\":75},\"dt\":1501250400,\"sys\":{\"type\":1,\"id\":7358,\"message\":0.0041,\"country\":\"UA\",\"sunrise\":1501208450,\"sunset\":1501264023},\"id\":696050,\"name\":\"Pushcha-Voditsa\",\"cod\":200}", Weather.class);
        weatherMapper = new WeatherMapper();
    }

    @Test
    public void mapsWeatherToWeatherData() throws Exception {
        WeatherData weatherData = weatherMapper.apply(weather);
        assertEquals("Clouds", weatherData.getCondition());
        assertEquals("broken clouds", weatherData.getDescription());
        assertEquals(78, weatherData.getHumidity());
        assertEquals(1004.0, weatherData.getPressure());
        assertEquals(24.34f, weatherData.getTemperature());
        assertEquals(6.0f, weatherData.getWindSpeed());
        assertEquals(R.string.wind_n, weatherData.getWindFormat());
        assertEquals("http://openweathermap.org/img/w/04d.png", weatherData.getIconUri());

    }
}
