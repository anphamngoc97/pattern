package com.example.myapplication.view.databinding;

import android.databinding.DataBindingComponent;

public class RecyclerBinding implements android.databinding.DataBindingComponent {
    @Override
    public ListNoteBinding getListNoteBinding() {
        return new ListNoteBinding();
    }
}