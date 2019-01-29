package com.example.myapplication.view.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.myapplication.service.model.Note;
import com.example.myapplication.view.adapter.NoteAdapter;

import java.util.ArrayList;

public class ListNoteBinding {
    @BindingAdapter({"app:adapter","app:data"})
    public void bind(RecyclerView recyclerView, NoteAdapter adapter, ArrayList<Note> notes){
        recyclerView.setAdapter(adapter);
        adapter.setNoteList(notes);
    }
}
