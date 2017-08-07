package com.gcteam.yamblz.homework.presentation.view.city;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.WeatherApplication;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;
import com.gcteam.yamblz.homework.presentation.adapter.cities.FilteredCitiesAdapter;
import com.gcteam.yamblz.homework.presentation.di.component.CityScreenComponent;
import com.gcteam.yamblz.homework.presentation.presenter.city.CityChooserPresenter;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;


public class CityFilterActivity extends AppCompatActivity implements CityFilterView, FilteredCitiesAdapter.OnCityClickListener {

    public static final String INTENT_EXTRA_CITY = "city";

    public static final int CITY_FILTER_REQUEST_CODE = 2;

    CityScreenComponent cityScreenComponent;

    @Inject
    CityChooserPresenter presenter;

    @BindView(R.id.filtered_cities)
    RecyclerView filteredCities;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.loading_spinner)
    ProgressBar loadingSpinner;
    EditText cityFilter;

    FilteredCitiesAdapter filteredCitiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_chooser);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View actionBarView = LayoutInflater.from(this)
                .inflate(R.layout.toolbar_city_filter, toolbar);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        cityFilter = ButterKnife.findById(actionBarView, R.id.filter_edit_text);

        cityScreenComponent = WeatherApplication.getComponentManager().getAppComponent()
                .getCityComponent()
                .getCityScreenComponent();
        cityScreenComponent.inject(this);


        filteredCitiesAdapter = new FilteredCitiesAdapter(getLayoutInflater(), this);
        filteredCities.setAdapter(filteredCitiesAdapter);
        filteredCities.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        filteredCities.setItemAnimator(new DefaultItemAnimator());

        presenter.onAttach(this);
    }

    @Override
    public void showLoadingSpinner() {
        filteredCitiesAdapter.clear();
        loadingSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingSpinner() {
        loadingSpinner.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showChosenCities(@NonNull List<FilteredCity> filteredCities) {
        if (filteredCities.size() == 0) {
            showError();
        } else {
            filteredCitiesAdapter.insertAll(filteredCities);
        }
    }

    @Override
    public void hideChosenCities() {
        filteredCitiesAdapter.clear();
    }


    @Override
    public Observable<String> getFilterObs() {
        return RxTextView.afterTextChangeEvents(cityFilter)
                .map(textViewAfterTextChangeEvent ->
                        textViewAfterTextChangeEvent.editable().toString());
    }

    @Override
    public void showError() {
        loadingSpinner.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onCityClick(FilteredCity filteredCity) {
        Intent intent = new Intent();
        intent.putExtra(INTENT_EXTRA_CITY, filteredCity);
        setResult(RESULT_OK, intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
