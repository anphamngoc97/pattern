package com.example.myapplication.viewmodel;

import android.databinding.BaseObservable;

import com.example.myapplication.service.model.Note;

public class NoteDataViewModel extends BaseObservable {
    public Note note;

    public NoteDataViewModel(Note note) {
        this.note = note;
    }
}
