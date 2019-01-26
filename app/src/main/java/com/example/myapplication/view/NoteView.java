package com.example.myapplication.view;

import com.example.myapplication.model.Note;

import java.util.ArrayList;

public interface NoteView {
    void setItem(ArrayList<Note> notes);
    void refreshList();

}
