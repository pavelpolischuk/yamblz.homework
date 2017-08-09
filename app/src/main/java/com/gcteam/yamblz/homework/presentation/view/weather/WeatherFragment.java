package com.gcteam.yamblz.homework.presentation.view.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.WeatherApplication;
import com.gcteam.yamblz.homework.domain.object.FullWeatherReport;
import com.gcteam.yamblz.homework.presentation.adapter.weather.FullWeatherAdapter;
import com.gcteam.yamblz.homework.presentation.di.component.WeatherScreenComponent;
import com.gcteam.yamblz.homework.presentation.presenter.weather.WeatherPresenter;
import com.gcteam.yamblz.homework.presentation.view.BaseFragment;
import com.gcteam.yamblz.homework.presentation.view.main.TitlePicker;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by turist on 15.07.2017.
 */

public class WeatherFragment extends BaseFragment implements WeatherView, SharedPreferences.OnSharedPreferenceChangeListener {

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.forecast)
    RecyclerView forecast;


    @Inject
    WeatherPresenter weatherPresenter;
    @Inject
    PreferencesManager preferencesManager;

    SharedPreferences prefs;
    TitlePicker titlePicker;
    WeatherScreenComponent weatherScreenComponent;
    FullWeatherAdapter fullWeatherAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return bind(inflater.inflate(R.layout.fragment_weather, container, false));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.titlePicker = (TitlePicker) context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        weatherScreenComponent = WeatherApplication.getComponentManager().getAppComponent()
                .getWeatherComponent()
                .getWeatherScreenComponent();
        weatherScreenComponent.inject(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        prefs.registerOnSharedPreferenceChangeListener(this);

        weatherPresenter.onAttach(this);
        if (savedInstanceState == null) {
            weatherPresenter.refreshForecast(preferencesManager);
        }
        refreshLayout.setOnRefreshListener(() -> weatherPresenter.refreshForecast(preferencesManager));

        fullWeatherAdapter = new FullWeatherAdapter();
        forecast.setAdapter(fullWeatherAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        forecast.setLayoutManager(layoutManager);
        forecast.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        forecast.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        weatherPresenter.onDetach();
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void showLoadingStarted() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void showFullWeather(FullWeatherReport fullWeatherReport) {
        fullWeatherAdapter.insertFullWeather(fullWeatherReport);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void changeTitle(String title) {
        titlePicker.setToolbarTitle(title);
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(refreshLayout, R.string.loading_error, BaseTransientBottomBar.LENGTH_LONG).show();
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        weatherPresenter.refreshForecast(preferencesManager);
    }
}