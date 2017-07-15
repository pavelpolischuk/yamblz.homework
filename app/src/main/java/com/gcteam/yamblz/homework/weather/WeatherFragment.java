package com.gcteam.yamblz.homework.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcteam.yamblz.homework.R;
import com.gcteam.yamblz.homework.utils.RxKnifeFragment;

/**
 * Created by turist on 15.07.2017.
 */

public class WeatherFragment extends RxKnifeFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return bind(inflater.inflate(R.layout.fragment_weather, container, false));
    }
}
