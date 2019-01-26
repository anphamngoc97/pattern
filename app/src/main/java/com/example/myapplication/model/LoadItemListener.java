package com.example.myapplication.model;

import java.util.ArrayList;

public interface LoadItemListener {
    void onLoadFinish(ArrayList<Note> notes);
    void onChangeFinish();
}
