package com.gcteam.yamblz.homework.presentation.di.component;

import com.gcteam.yamblz.homework.presentation.di.module.CityScreenModule;
import com.gcteam.yamblz.homework.presentation.di.scope.CityChooserScreenScope;
import com.gcteam.yamblz.homework.presentation.view.cities.CityChooserActivity;

import dagger.Subcomponent;

/**
 * Created by Kim Michael on 03.08.17
 */
@Subcomponent(modules = {CityScreenModule.class})
@CityChooserScreenScope
public interface CityScreenComponent {

    void inject(CityChooserActivity cityChooserActivity);
}
