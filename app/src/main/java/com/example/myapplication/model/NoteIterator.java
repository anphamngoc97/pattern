package com.example.myapplication.model;

public interface NoteIterator {
    void load(LoadItemListener loadItemListener);
    long addNote(LoadItemListener itemListener, String title);
    void delNote(LoadItemListener itemListener, int position);
}
