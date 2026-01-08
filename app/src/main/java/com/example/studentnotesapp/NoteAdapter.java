package com.example.studentnotesapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.Holder> {

    Context context;
    Cursor cursor;

    public NoteAdapter(Context c, Cursor cur) {
        context = c;
        cursor = cur;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.note_item, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder h, int pos) {
        cursor.moveToPosition(pos);
        h.title.setText(cursor.getString(1));
        h.content.setText(cursor.getString(2));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView title, content;
        Holder(View v) {
            super(v);
            title = v.findViewById(R.id.txtTitle);
            content = v.findViewById(R.id.txtContent);
        }
    }
}
