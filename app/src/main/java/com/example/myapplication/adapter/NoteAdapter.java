package com.example.myapplication.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Note;
import com.example.myapplication.presenter.NoteItemListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.Holder> {
    ArrayList<Note> notes;
    Context context;
    NoteItemListener onClickItem;

    public NoteAdapter(ArrayList<Note> notes, Context context, NoteItemListener onClickItem) {
        this.notes = notes;
        this.context = context;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note,viewGroup,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.txtvNote.setText(notes.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtvNote)
        TextView txtvNote;
        @BindView(R.id.btnDel)
        Button btnDel;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            btnDel.setOnClickListener(v-> onClickItem.onRemove(getAdapterPosition()));
            txtvNote.setOnClickListener(v->onClickItem.onTextClick(txtvNote));
        }
    }
}

