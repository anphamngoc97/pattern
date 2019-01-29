package com.example.myapplication.view.callback;

import android.view.View;
import android.widget.TextView;

public interface ItemClickCallback {
    void onRemove(int position);
    void onTextClick(View textView);
}
