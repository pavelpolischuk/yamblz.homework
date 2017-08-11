package com.gcteam.yamblz.homework.data.repository.weather;

import com.gcteam.yamblz.homework.data.local.weather.WeatherStorage;
import com.gcteam.yamblz.homework.data.mapper.ForecastResponseMapper;
import com.gcteam.yamblz.homework.data.network.weather.WeatherService;

import org.junit.Before;
import org.mockito.Mock;

/**
 * Created by Kim Michael on 11.08.17
 */
public class WeatherRepositoryTest {

    WeatherRepository weatherRepository;
    @Mock
    WeatherStorage weatherStorage;
    @Mock
    WeatherService weatherService;
    ForecastResponseMapper forecastResponseMapper;

    @Before
    public void setup() {
        forecastResponseMapper = new ForecastResponseMapper();
        weatherRepository = new WeatherRepository(weatherStorage, weatherService, forecastResponseMapper);
    }
}
