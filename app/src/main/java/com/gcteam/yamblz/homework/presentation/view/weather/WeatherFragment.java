package com.gcteam.yamblz.homework.presentation.view.weather;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.WeatherApplication;
import com.gcteam.yamblz.homework.domain.object.WeatherData;
import com.gcteam.yamblz.homework.presentation.di.component.WeatherScreenComponent;
import com.gcteam.yamblz.homework.presentation.presenter.weather.WeatherPresenter;
import com.gcteam.yamblz.homework.presentation.view.BaseFragment;
import com.gcteam.yamblz.homework.presentation.view.main.TitlePicker;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by turist on 15.07.2017.
 */

public class WeatherFragment extends BaseFragment implements WeatherView {

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout refreshLayout;

    TitlePicker titlePicker;

    @Inject
    WeatherPresenter weatherPresenter;

    WeatherScreenComponent weatherScreenComponent;

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

        weatherPresenter.onAttach(this);
        if (savedInstanceState == null) {
            weatherPresenter.startRefresh();
        }
        refreshLayout.setOnRefreshListener(() -> weatherPresenter.startRefresh());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        weatherPresenter.onDetach();
    }

    @Override
    public void showWeatherData(WeatherData weather) {

        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadingStarted() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(refreshLayout, R.string.loading_error, BaseTransientBottomBar.LENGTH_LONG).show();
        refreshLayout.setRefreshing(false);
    }
}