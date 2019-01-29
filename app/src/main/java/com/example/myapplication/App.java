package com.example.myapplication;

import android.app.Application;
import android.databinding.DataBindingUtil;

import com.example.myapplication.view.databinding.RecyclerBinding;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataBindingUtil.setDefaultComponent(new RecyclerBinding());
    }
}
