package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.adapter.NoteAdapter;
import com.example.myapplication.model.Note;
import com.example.myapplication.presenter.NoteItemListener;
import com.example.myapplication.presenter.NotePresenter;
import com.example.myapplication.view.NoteView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NoteView, NoteItemListener {

    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.editNote)
    EditText editNote;
    @BindView(R.id.recycleNote)
    RecyclerView recycleNote;
    NoteAdapter adapter;

    NotePresenter notePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        onClick();
    }

    private void init(){
        notePresenter = new NotePresenter(getApplicationContext(),this);
    }
    private void onClick(){
        btnAdd.setOnClickListener(v->{
            String title = editNote.getText().toString().trim();
            if(!TextUtils.isEmpty(title)){
                notePresenter.addNote(title);
            }
        });
    }
    @Override
    public void onRemove(int position) {
        notePresenter.delNote(position);
    }

    @Override
    public void onTextClick(TextView textView) {
        if(textView.getMaxLines()!=5){
            textView.setMaxLines(5);
        }else{
            textView.setMaxLines(1);
        }
    }

    @Override
    public void setItem(ArrayList<Note> notes) {
        adapter = new NoteAdapter(notes,getApplicationContext(),this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycleNote.setAdapter(adapter);
        recycleNote.setLayoutManager(layoutManager);
    }

    @Override
    public void refreshList() {
        adapter.notifyDataSetChanged();
    }
}
