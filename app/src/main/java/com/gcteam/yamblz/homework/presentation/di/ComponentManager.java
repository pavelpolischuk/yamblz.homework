package com.gcteam.yamblz.homework.presentation.di;

import android.app.Application;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import com.gcteam.yamblz.homework.presentation.di.component.AppComponent;
import com.gcteam.yamblz.homework.presentation.di.component.DaggerAppComponent;
import com.gcteam.yamblz.homework.presentation.di.module.AppModule;

/**
 * Created by Kim Michael on 01.08.17
 */
public class ComponentManager {
    private AppComponent appComponent;

    @MainThread
    public ComponentManager(@NonNull Application app) {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(app))
                .build();
    }

    @NonNull
    @MainThread
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
