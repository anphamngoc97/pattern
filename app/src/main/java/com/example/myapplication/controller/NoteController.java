package com.example.myapplication.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.myapplication.entities.Note;
import com.example.myapplication.model.NoteModel;
import com.example.myapplication.view.NoteView;

import java.util.ArrayList;

public class NoteController {
    NoteModel noteModel;
    ArrayList<Note> notes;
    NoteView view;

    public NoteController(Context context, ArrayList<Note> notes, NoteView view){
        noteModel = new NoteModel(context);
        this.notes=notes;
        this.view = view;

        getAllNote();
    }
    public void addNote(String title){
        notes.add(new Note(title));

        ContentValues values = new ContentValues();
        values.put(noteModel.columns[1],title);

        long id = noteModel.addNote(values);
        notes.get(notes.size()-1).setId(id);

        view.updateList();
    }
    public void delNote(long id){
        noteModel.delTask(noteModel.columns[0],id);
        view.updateList();
    }

    public void getAllNote(){
        Cursor cursor = noteModel.getAll();
        notes.clear();
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){

                Note note = new Note(cursor.getString(1));
                note.setId(cursor.getLong(1));
                notes.add(note);
            }
        }
        view.initList();
    }

}
