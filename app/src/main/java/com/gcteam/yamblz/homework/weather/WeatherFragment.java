package com.gcteam.yamblz.homework.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.settings.PreferencesManager;
import com.gcteam.yamblz.homework.utils.RxKnifeFragment;
import com.gcteam.yamblz.homework.weather.api.Weather;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by turist on 15.07.2017.
 */

public class WeatherFragment extends RxKnifeFragment implements WeatherLoadingView {

    @BindView(R.id.swiperefresh) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.icon) AppCompatImageView icon;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.temperature) TextView temperature;
    @BindView(R.id.pressure) TextView pressure;
    @BindView(R.id.humidity) TextView humidity;
    @BindView(R.id.wind) TextView wind;
    @BindView(R.id.updated) TextView updated;

    private WeatherLoadingInteractor interactor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return bind(inflater.inflate(R.layout.fragment_weather, container, false));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        interactor = new WeatherLoadingInteractor(new PreferencesManager(getContext()));
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        interactor.bind(this);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                save(interactor.startRefresh());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        save(interactor.startRefresh());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        interactor.unbind();
    }

    @Override
    public void loaded(Weather weather) {
        Picasso.with(getContext()).load(weather.getIconUri()).into(icon);
        description.setText(weather.getDescription());
        temperature.setText(String.format("%.1f°C", weather.getTemperature()));
        pressure.setText(String.format("%s hPa", weather.getPressure()));
        humidity.setText(String.format("%s%%", weather.getHumidity()));
        wind.setText(String.format(getString(weather.getWindFormat()), weather.getWindSpeed()));
        refreshLayout.setRefreshing(false);
        updated.setText(weather.getUpdatingTime().getTime().toString());
    }

    @Override
    public void loadingStart() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void loadingError() {
        Snackbar.make(refreshLayout, R.string.loading_error, BaseTransientBottomBar.LENGTH_LONG).show();
        refreshLayout.setRefreshing(false);
    }
}