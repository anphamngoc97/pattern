package com.example.myapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.myapplication.service.load.LoadItemListener;
import com.example.myapplication.service.load.NoteIteratorImpl;
import com.example.myapplication.service.model.Note;
import com.example.myapplication.service.model.NoteIterator;
import com.example.myapplication.view.adapter.NoteAdapter;

import java.util.ArrayList;

public class ListViewModel extends AndroidViewModel implements LoadItemListener {
    public ArrayList<Note> model;
    public NoteAdapter adapter;
    NoteIterator iterator;
    public ListViewModel(@NonNull Application application) {
        super(application);
        model = new ArrayList<>();
        adapter = new NoteAdapter(getApplication().getApplicationContext(),application);
        iterator = new NoteIteratorImpl(getApplication());

        setUp();
    }

    public void onRemove(){

    }
    public void onTextClick(){

    }
    public void setUp(){
        Log.d("AAA","listviewmodel: " + "loading");
        iterator.load(this);
    }

    @Override
    public void onLoadFinish(ArrayList<Note> notes) {
        Log.d("AAA","load finish: "+notes.size());
        model = notes;

    }

    @Override
    public void onChangeFinish() {

    }
}
