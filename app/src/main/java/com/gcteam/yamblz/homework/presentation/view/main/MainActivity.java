package com.gcteam.yamblz.homework.presentation.view.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.WeatherApplication;
import com.gcteam.yamblz.homework.domain.object.ChosenCity;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;
import com.gcteam.yamblz.homework.domain.update.weather.UpdateWeatherJob;
import com.gcteam.yamblz.homework.presentation.adapter.cities.CitySummariesAdapter;
import com.gcteam.yamblz.homework.presentation.di.component.CityChooserComponent;
import com.gcteam.yamblz.homework.presentation.presenter.city.CityPickerPresenter;
import com.gcteam.yamblz.homework.presentation.presenter.main.MainActivityRouter;
import com.gcteam.yamblz.homework.presentation.view.city.CityFilterActivity;
import com.gcteam.yamblz.homework.utils.PreferencesManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements TitlePicker, CityChooserView, CitySummariesAdapter.OnCityClickListener {

    private static final String TITLE_KEY = "title";
    private static final int PERMISSION_REQUEST_CODE = 1;
    @Inject
    CityPickerPresenter cityPickerPresenter;
    @Inject
    PreferencesManager preferencesManager;
    CitySummariesAdapter citySummariesAdapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.city_chooser)
    RecyclerView citySummaries;
    CityChooserComponent cityChooserComponent;
    private MainActivityRouter router = new MainActivityRouter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cityChooserComponent = WeatherApplication.getComponentManager()
                .getAppComponent()
                .getCityComponent()
                .getCityChooserComponent();
        cityChooserComponent.inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.INTERNET}, PERMISSION_REQUEST_CODE);
        }

        if (savedInstanceState == null) {
            router.showWeather();

            if (!UpdateWeatherJob.checkStarted()) {
                UpdateWeatherJob.startUpdate(preferencesManager.getUpdateInterval());
            }
        }

        citySummariesAdapter = new CitySummariesAdapter(getLayoutInflater(), this);
        citySummaries.setAdapter(citySummariesAdapter);
        citySummaries.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        citySummaries.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void setToolbarTitle(String title) {
        setTitle(title);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setTitle(savedInstanceState.getString(TITLE_KEY, ""));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TITLE_KEY, getTitle().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CityFilterActivity.CITY_FILTER_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    FilteredCity chosenCity = data.getParcelableExtra(
                            CityFilterActivity.INTENT_EXTRA_CITY);
                    addChosenCity(chosenCity);
                }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.nav_weather)
    public void onWeatherNavigationClick() {
        router.showWeather();
        drawer.closeDrawer(GravityCompat.START);
    }

    @OnClick(R.id.nav_settings)
    public void onSettingsNavigationClick() {
        router.showSettings();
        drawer.closeDrawer(GravityCompat.START);
    }

    @OnClick(R.id.nav_about)
    public void onAboutNavigationClick() {
        router.showAbout();
        drawer.closeDrawer(GravityCompat.START);
    }

    @OnClick(R.id.nav_city_picker)
    public void onCityPickerClick() {
        Intent intent = new Intent(getApplicationContext(), CityFilterActivity.class);
        startActivityForResult(intent, CityFilterActivity.CITY_FILTER_REQUEST_CODE);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                boolean denied = grantResults[0] != PackageManager.PERMISSION_GRANTED;
                if (denied) {
                    finish();
                } else {
                    recreate();
                }
                break;
        }
    }

    @Override
    public void addChosenCity(FilteredCity chosenCity) {
        if (citySummariesAdapter.insert(ChosenCity.from(chosenCity))) {
            cityPickerPresenter.addCity(chosenCity);
        }
    }

    public void chooseCity() {

    }

    @Override
    public void deleteCity(String cityName) {

    }

    @Override
    public void onCityClick(ChosenCity chosenCity) {

    }
}
