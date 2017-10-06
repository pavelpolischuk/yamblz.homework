package com.gcteam.yamblz.homework.presentation.di.component;

import com.gcteam.yamblz.homework.presentation.di.module.CityChooserModule;
import com.gcteam.yamblz.homework.presentation.di.scope.CityChooserScope;
import com.gcteam.yamblz.homework.presentation.view.main.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Kim Michael on 06.08.17
 */
@Subcomponent(modules = {CityChooserModule.class})
@CityChooserScope
public interface CityChooserComponent {

    void inject(MainActivity mainActivity);
}
