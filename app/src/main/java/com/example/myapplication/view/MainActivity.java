package com.example.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.NoteAdapter;
import com.example.myapplication.adapter.OnClickItem;
import com.example.myapplication.controller.NoteController;
import com.example.myapplication.entities.Note;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NoteView, OnClickItem {

    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.editNote)
    EditText editNote;
    @BindView(R.id.recycleNote)
    RecyclerView recycleNote;
    NoteAdapter adapter;
    ArrayList<Note> notes = new ArrayList<>();

    NoteController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        controller = new NoteController(getApplicationContext(),notes,this);

        onClick();

    }


    private void onClick(){
        btnAdd.setOnClickListener(v->{
            String title = editNote.getText().toString().trim();
            if(!TextUtils.isEmpty(title)){
                controller.addNote(title);
            }
        });
    }

    @Override
    public void initList() {
        Log.d("AAA"," note: " + notes);
        adapter = new NoteAdapter(notes, getApplicationContext(), this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycleNote.setLayoutManager(layoutManager);
        recycleNote.setAdapter(adapter);
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRemoveClick(int position) {

        controller.delNote(position,notes.get(position).getId());
    }

    @Override
    public void onTextClick(TextView textView) {
        if(textView.getMaxLines()!=10) {
            textView.setMaxLines(10);
        }else{
            textView.setMaxLines(1);
        }


    }
}
