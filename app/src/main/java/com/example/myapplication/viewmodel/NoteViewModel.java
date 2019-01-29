package com.example.myapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.myapplication.service.load.LoadItemListener;
import com.example.myapplication.service.load.NoteIteratorImpl;
import com.example.myapplication.service.model.NoteIterator;
import com.example.myapplication.view.adapter.NoteAdapter;
import com.example.myapplication.service.model.Note;

import java.util.ArrayList;

public class NoteViewModel extends BaseObservable implements LoadItemListener {

    private static NoteViewModel noteViewModel = null;
    private static MutableLiveData<ArrayList<Note>> notes = new MutableLiveData<>();
    public static NoteAdapter adapter;

    private static NoteIterator iterator;
    public String textNote;

    public static NoteViewModel getInstance(Application application){

        if( noteViewModel == null){
            noteViewModel = new NoteViewModel();
            iterator = new NoteIteratorImpl(application);
            adapter = new NoteAdapter(application.getApplicationContext(), application);
        }
        return noteViewModel;
    }

    public void load(){
        iterator.load(this);
    }
    public void addNote(){

        if(textNote!=null && textNote.trim().length()!=0){

            long id = iterator.addNote(this,textNote);
            ArrayList t = notes.getValue();
           // t.add(new Note(textNote,id));
            notes.setValue(t);

            adapter.setNoteList(notes.getValue());

        }
    }
    public void delNote(long id){
        if(id==-1) return;

        ArrayList<Note> t = notes.getValue();
        int position = -1;
        for(int i=0;i<t.size();i++){
            if(t.get(i).getId() == id){
                position = i;
                break;
            }
        }
        t.remove(position);
        notes.setValue(t);
        iterator.delNote(this,id);
    }

    public ArrayList<Note> getNotes(){
        return notes.getValue();
    }

    @Override
    public void onLoadFinish(ArrayList<Note> notes) {

        this.notes.setValue(notes);
        //adapter.notifyDataSetChanged();
        adapter.setNoteList(notes);
    }

    @Override
    public void onChangeFinish() {
            adapter.notifyDataSetChanged();
    }

    public static class Factory extends ViewModelProviders.DefaultFactory{

        Application application;

        public Factory(@NonNull Application application) {
            super(application);
            this.application = application;
        }
    }

    
}
