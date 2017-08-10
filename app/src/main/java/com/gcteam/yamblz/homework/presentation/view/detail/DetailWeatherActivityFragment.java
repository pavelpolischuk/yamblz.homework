package com.gcteam.yamblz.homework.presentation.view.detail;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcteam.yamblz.homework.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailWeatherActivityFragment extends Fragment {

    public DetailWeatherActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_weather, container, false);
    }
}
