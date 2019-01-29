package com.example.myapplication.service.model;

import com.example.myapplication.service.load.LoadItemListener;

public interface NoteIterator {
    void load(LoadItemListener loadItemListener);
    long addNote(LoadItemListener itemListener, String title);
    void delNote(LoadItemListener itemListener, long id);

}
