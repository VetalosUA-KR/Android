package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Edite_note_activity extends AppCompatActivity {

    private TextView textViewTitle;
    private TextView textViewDescription;

    //private NotesAdapter adapter;

    //private MainViewModel viewModel;

    private NotesDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_note_activity);

        //viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        /*adapter = new NotesAdapter(MainActivity.notes);*/
        textViewTitle = findViewById(R.id.editTextTitle);
        textViewDescription = findViewById(R.id.editTextDescription);

        Intent intent = getIntent();
        textViewTitle.setText(intent.getStringExtra("title"));
        textViewDescription.setText(intent.getStringExtra("description"));
        int pos = intent.getIntExtra("note", 0);


        //Note tmpNote = dao.getNoteById(id);

        dao = NotesDatabase.getInstance(this).notesDao();
    }

    public void onClickUpdateNote(View view) {

        Intent getIntent = getIntent();
        int id_note = getIntent.getIntExtra("id_note",1);

        Note note = dao.getNoteById(id_note);
        //viewModel.updateNote(note);
        note.setTitle("New TITLE");
        note.setDescription("New DESCRIPTION");
        Log.i("testUPD", note.getTitle());
        //dao.deletNote(note);
        dao.updateNote(note);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}