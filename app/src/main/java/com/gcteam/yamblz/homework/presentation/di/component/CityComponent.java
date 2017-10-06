package com.gcteam.yamblz.homework.presentation.di.component;

import com.gcteam.yamblz.homework.presentation.di.module.CityModule;
import com.gcteam.yamblz.homework.presentation.di.scope.CityFilterScope;

import dagger.Subcomponent;

/**
 * Created by Kim Michael on 03.08.17
 */
@Subcomponent(modules = {CityModule.class})
@CityFilterScope
public interface CityComponent {

    CityChooserComponent getCityChooserComponent();

    CityScreenComponent getCityScreenComponent();
}
