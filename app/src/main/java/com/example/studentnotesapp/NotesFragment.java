package com.example.studentnotesapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NotesFragment extends Fragment {

    RecyclerView recyclerView;
    TextView txtEmpty;
    Button btnAdd;
    NoteDatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        txtEmpty = view.findViewById(R.id.txtEmpty);
        btnAdd = view.findViewById(R.id.btnAddNote);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dbHelper = new NoteDatabaseHelper(getContext());

        btnAdd.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), NoteActivity.class))
        );

        loadNotes();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadNotes();
    }

    private void loadNotes() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notes", null);

        if (cursor.getCount() == 0) {
            txtEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            txtEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new NoteAdapter(getContext(), cursor));
        }
    }
}
