package com.example.myapplication.presenter;

import android.widget.TextView;

public interface NoteItemListener {
    void onRemove(int position);
    void onTextClick(TextView textView);
}
