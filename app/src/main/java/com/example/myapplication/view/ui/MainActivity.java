package com.example.myapplication.view.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.service.model.Note;
import com.example.myapplication.view.adapter.NoteAdapter;
import com.example.myapplication.view.callback.AddClickCallback;
import com.example.myapplication.viewmodel.ListViewModel;
import com.example.myapplication.viewmodel.NoteViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycleNote)
    RecyclerView recyclerView;

    ActivityMainBinding binding;

    NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        View view = initDataBinding();
        ButterKnife.bind(this);


        noteViewModel.load();

        //registerLiveDataListener();
        action();
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteViewModel.load();

    }

    private View initDataBinding(){
        //NoteViewModel.Factory factory = new NoteViewModel.Factory(getApplication());


        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        //noteViewModel = ViewModelProviders.of(this,factory).get(NoteViewModel.class);
        noteViewModel = NoteViewModel.getInstance(getApplication());

        Log.d("AAA","initdatabinnd: " + noteViewModel);
        binding.setViewModel(noteViewModel);

        //binding.setCallback(addClickCallback);

        return binding.getRoot();
    }
    private void action(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

       // setupRecyclerView();


    }

    void setupRecyclerView(){

        NoteAdapter adapter = new NoteAdapter(getApplication(),noteViewModel.getNotes());
        recyclerView.setAdapter(adapter);

        //adapter.setNoteList(noteViewModel.getNotes());


    }
    private void registerLiveDataListener(){
//        noteViewModel.getTitle().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                txtv1.setText(s);
//                txtv2.setText(s);
//            }
//        });

    }
//    final AddClickCallback addClickCallback = new AddClickCallback() {
//        @Override
//        public void onClick() {
//            noteViewModel.addNote();
//        }
//    };
}
