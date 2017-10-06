package com.gcteam.yamblz.homework.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Kim Michael on 16.07.17
 */
public abstract class BasePresenter<T extends BaseView> {

    private T view;

    public void onAttach(@NonNull T view) {
        this.view = view;
    }

    public void onDetach() {
        this.view = null;
    }

    @Nullable
    public T getView() {
        return view;
    }
}
