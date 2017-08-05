package com.gcteam.yamblz.homework.presentation.di.component;

import com.gcteam.yamblz.homework.presentation.di.module.CityModule;
import com.gcteam.yamblz.homework.presentation.di.scope.CityChooserScope;

import dagger.Subcomponent;

/**
 * Created by Kim Michael on 03.08.17
 */
@Subcomponent(modules = {CityModule.class})
@CityChooserScope
public interface CityComponent {

    CityScreenComponent getCityScreenComponent();
}
