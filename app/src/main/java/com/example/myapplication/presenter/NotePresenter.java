package com.example.myapplication.presenter;

import android.content.Context;

import com.example.myapplication.model.LoadItemListener;
import com.example.myapplication.model.Note;
import com.example.myapplication.model.NoteIterator;
import com.example.myapplication.model.NoteIteratorImp;
import com.example.myapplication.view.NoteView;

import java.util.ArrayList;

public class NotePresenter implements Presenter, LoadItemListener {
    NoteView view;
    NoteIterator iterator;

    public NotePresenter(Context context,NoteView view){
        this.view = view;
        iterator = new NoteIteratorImp(context);
        iterator.load(this);
    }

    @Override
    public void onLoadFinish(ArrayList<Note> notes) {
        view.setItem(notes);
    }

    @Override
    public void onChangeFinish() {
        view.refreshList();
    }

    @Override
    public void addNote(String title) {
        iterator.addNote(this,title);
    }

    @Override
    public void delNote(int position) {
        iterator.delNote(this,position);
    }
}
