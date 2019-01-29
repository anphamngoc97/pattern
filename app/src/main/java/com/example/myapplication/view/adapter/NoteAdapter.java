package com.example.myapplication.view.adapter;

import android.app.Application;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemNoteBinding;
import com.example.myapplication.service.model.Note;
import com.example.myapplication.view.callback.ItemClickCallback;
import com.example.myapplication.viewmodel.NoteDataViewModel;
import com.example.myapplication.viewmodel.NoteViewModel;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.Holder> {
    ArrayList<Note> notes;
    Context context;
    Application application;
    ItemClickCallback onClickItem;

    public NoteAdapter(Application application,ArrayList<Note> notes){
        this.application = application;
        this.notes = notes;
        this.context = application.getApplicationContext();

    }
    public NoteAdapter(Context context, Application application) {
        //this.notes = notes;
        this.context = context;
        this.application = application;
    }



    public void setNoteList(final ArrayList<Note> noteList){
        Log.d("AAA","db adapter: " + noteList.size());
        if(this.notes == null){
            this.notes = noteList;
            notifyItemRangeInserted(0,noteList.size());
        }else{

            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return NoteAdapter.this.notes.size();
                }

                @Override
                public int getNewListSize() {
                    return noteList.size();
                }

                @Override
                public boolean areItemsTheSame(int i, int i1) {
                    return NoteAdapter.this.notes.get(i).getId()==NoteAdapter.this.notes.get(i1).getId();
                }

                @Override
                public boolean areContentsTheSame(int i, int i1) {
                    return false;
                }
            });
            this.notes = noteList;
            result.dispatchUpdatesTo(this);
        }
    }


    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note,viewGroup,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.setUp(notes.get(i).getId(),i);
        holder.binding.setModel(notes.get(i));

//        holder.binding.setModel(notes.get(i));
//        holder.binding.setCallback(onClickItem);
//        holder.binding.setIndex(i);

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ItemNoteBinding binding;
        NoteViewModel viewModel;
        long id = -1;
        int position = -1;

        public Holder(@NonNull final View view) {
            super(view);

            binding = DataBindingUtil.bind(view);
            binding.setId(-1L);
            binding.setPosition(-1);

            viewModel = NoteViewModel.getInstance(application);

            onClickItem = new ItemClickCallback() {
                @Override
                public void onRemove(int position) {

                    Holder.this.viewModel.delNote(Holder.this.id);
                }

                @Override
                public void onTextClick(View textView) {
                    if(textView instanceof TextView) {
                        TextView tv = (TextView)textView;

                        if (tv.getMaxLines() == 1) {
                            tv.setMaxLines(5);
                        } else {
                            tv.setMaxLines(1);
                        }
                    }
                }
            };
            binding.setCallback(onClickItem);
            binding.setViewmodel(viewModel);

        }
        public void setUp(long id,int position){
            if(this.id!=id|| this.position !=position){
                this.id = id;
                this.position = position;

                binding.setId(id);

                binding.setModel(notes.get(position));
            }

        }
    }

}

