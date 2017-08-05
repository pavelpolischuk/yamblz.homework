package com.gcteam.yamblz.homework.presentation.view.main;

import com.gcteam.yamblz.homework.domain.object.CitySummary;

import java.util.List;

/**
 * Created by Kim Michael on 04.08.17
 */
public interface CityPickerView {

    void showChosenCities(List<CitySummary> citySummaries);
}
