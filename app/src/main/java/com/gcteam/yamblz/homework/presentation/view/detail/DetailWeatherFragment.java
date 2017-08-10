package com.gcteam.yamblz.homework.presentation.view.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.gcteam.yamblz.homework.utils.WeatherFormatUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailWeatherFragment extends Fragment implements DetailWeatherViewer {

    public static final String EXTRA_WEATHER_ARG = "weather_arg";

    @BindView(R.id.detail_day)
    TextView day;
    @BindView(R.id.detail_date)
    TextView date;
    @BindView(R.id.detail_max_temp)
    TextView maxTemp;
    @BindView(R.id.detail_min_temp)
    TextView minTemp;
    @BindView(R.id.detail_icon)
    ImageView icon;
    @BindView(R.id.detail_description)
    TextView description;
    @BindView(R.id.detail_humidity)
    TextView humidity;
    @BindView(R.id.detail_pressure)
    TextView pressure;
    @BindView(R.id.detail_wind)
    TextView wind;

    DetailWeatherOwner detailWeatherOwner;

    public DetailWeatherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_weather, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getArguments() == null) {
            viewDetailWeather(detailWeatherOwner.getWeatherData());
        } else {
            Bundle bundle = getArguments();
            viewDetailWeather(bundle.getParcelable(EXTRA_WEATHER_ARG));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetailWeatherOwner) {
            detailWeatherOwner = ((DetailWeatherOwner) context);
        }
    }

    @Override
    public void viewDetailWeather(@NonNull WeatherData weatherData) {
        day.setText(WeatherFormatUtils.getFormattedDay(weatherData.getDate()));
        date.setText(WeatherFormatUtils.getFormattedDate(weatherData.getDate()));
        icon.setImageDrawable(ContextCompat.getDrawable(getContext(),
                (WeatherFormatUtils.getArtResourceForWeatherCondition(
                        weatherData.getWeatherId()))));
        maxTemp.setText(WeatherFormatUtils.formatTemperature(getContext(), weatherData.getMaxTemp()));
        minTemp.setText(WeatherFormatUtils.formatTemperature(getContext(), weatherData.getMinTemp()));
        wind.setText(WeatherFormatUtils.getFormattedWind(getContext(), weatherData.getWindSpeed(), weatherData.getWindDeg()));
        humidity.setText(String.format(getContext().getString(R.string.format_humidity), weatherData.getHumidity()));
        pressure.setText(String.format(getContext().getString(R.string.format_pressure), weatherData.getPressure()));
        description.setText(WeatherFormatUtils.formatDescription(weatherData.getDescription()));
    }
}
