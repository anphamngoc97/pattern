package com.example.myapplication.service.load;

import com.example.myapplication.service.model.Note;

import java.util.ArrayList;

public interface LoadItemListener {
    void onLoadFinish(ArrayList<Note> notes);
    void onChangeFinish();

}
