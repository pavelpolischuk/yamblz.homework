package com.gcteam.yamblz.homework.presentation.view.main;

import com.gcteam.yamblz.homework.domain.object.FilteredCity;
import com.gcteam.yamblz.homework.presentation.BaseView;

/**
 * Created by Kim Michael on 04.08.17
 */
public interface CityChooserView extends BaseView {

    void addChosenCity(FilteredCity filteredCity);

    void deleteCity(String cityName);
}
