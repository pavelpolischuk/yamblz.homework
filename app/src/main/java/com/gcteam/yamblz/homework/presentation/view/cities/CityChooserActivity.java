package com.gcteam.yamblz.homework.presentation.view.cities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.WeatherApplication;
import com.gcteam.yamblz.homework.domain.object.FilteredCity;
import com.gcteam.yamblz.homework.presentation.adapter.cities.FilteredCitiesAdapter;
import com.gcteam.yamblz.homework.presentation.di.component.CityScreenComponent;
import com.gcteam.yamblz.homework.presentation.presenter.cities.CityChooserPresenter;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;


public class CityChooserActivity extends AppCompatActivity implements CityChooserView {

    private static final int FILTER_DEBOUNCE_TIME = 300;

    CityScreenComponent cityScreenComponent;

    @Inject
    CityChooserPresenter presenter;

    @BindView(R.id.filtered_cities)
    RecyclerView filteredCities;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    EditText cityFilter;

    FilteredCitiesAdapter filteredCitiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_chooser);
        filteredCitiesAdapter = new FilteredCitiesAdapter(getLayoutInflater());
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View actionBarView = LayoutInflater.from(this)
                .inflate(R.layout.toolbar_city_filter, null);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(actionBarView);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        cityFilter = ButterKnife.findById(actionBarView, R.id.filter_edit_text);

        cityScreenComponent = WeatherApplication.getComponentManager().getAppComponent()
                .getCityComponent()
                .getCityScreenComponent();
        cityScreenComponent.inject(this);

        presenter.onAttach(this);
    }

    @Override
    public void showLoadingSpinner() {

    }

    @Override
    public void hideLoadingSpinner() {

    }

    @Override
    public void showChosenCities(@NonNull List<FilteredCity> citySummaries) {

    }


    @Override
    public Observable<String> getFilterObs() {
        return RxTextView.afterTextChangeEvents(cityFilter)
                .debounce(FILTER_DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
                .map(textViewAfterTextChangeEvent -> textViewAfterTextChangeEvent.editable().toString());
    }
}
