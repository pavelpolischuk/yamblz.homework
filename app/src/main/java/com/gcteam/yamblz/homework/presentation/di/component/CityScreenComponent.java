package com.gcteam.yamblz.homework.presentation.di.component;

import com.gcteam.yamblz.homework.presentation.di.module.CityScreenModule;
import com.gcteam.yamblz.homework.presentation.di.scope.CityFilterScreenScope;
import com.gcteam.yamblz.homework.presentation.view.city.CityFilterActivity;

import dagger.Subcomponent;

/**
 * Created by Kim Michael on 03.08.17
 */
@Subcomponent(modules = {CityScreenModule.class})
@CityFilterScreenScope
public interface CityScreenComponent {

    void inject(CityFilterActivity cityFilterActivity);
}
