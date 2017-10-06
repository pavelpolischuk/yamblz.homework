package com.gcteam.yamblz.homework.presentation.view.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.domain.object.WeatherData;

public class DetailWeatherActivity extends AppCompatActivity implements DetailWeatherOwner {

    public static final String EXTRA_WEATHER_DATA = "weather_data_extra";
    private WeatherData weatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.weatherData = intent.getParcelableExtra(EXTRA_WEATHER_DATA);
        setContentView(R.layout.activity_detail_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public WeatherData getWeatherData() {
        return this.weatherData;
    }

}
