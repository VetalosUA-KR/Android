package com.example.mynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    private final ArrayList<Note> notes = new ArrayList<>();
    private NotesAdapter adapter;
    //private MainViewModel viewModel;

    private NotesDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }
        dao = NotesDatabase.getInstance(this).notesDao();

        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);

        adapter = new NotesAdapter(notes);
        //recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this)); // по вертикали
        //recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));//по горизонтали
        recyclerViewNotes.setLayoutManager(new GridLayoutManager(this, 2));//сетка

        getData();
        /*dao = NotesDatabase.getInstance(this).notesDao();
        LiveData<List<Note>> notesFromDB = dao.getAllNotes();
        notesFromDB.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notesFromLiveData) {
                adapter.setNotes(notesFromLiveData);
            }
        });*/

        recyclerViewNotes.setAdapter(adapter);
        adapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(int position) {

                String title = adapter.getNotes().get(position).getTitle();
                String description = adapter.getNotes().get(position).getDescription();
                Toast.makeText(MainActivity.this, "Clicked "+position+ "\ntitle: "+title, Toast.LENGTH_SHORT).show();
                /*Note note = adapter.getNotes().get(position);
                viewModel.updateNote(note);*/
                int id_note = adapter.getNotes().get(position).getId();

                Intent intent = new Intent(getApplicationContext(), Edite_note_activity.class);
                intent.putExtra("title", title);
                intent.putExtra("description", description);
                intent.putExtra("note", position);
                intent.putExtra("id_note", id_note);


                Log.i("idNote", dao.getNoteById(id_note).getTitle());


                //Log.i("idnote", );
                //intent.putExtra("note", adapter.getNotes().get(position));

                //Product product = new Product(name, company, price);

                //Intent intent = new Intent(this, SecondActivity.class);
                //intent.putExtra("keys", tmp);
                //startActivity(intent);

                startActivity(intent);
            }

            @Override
            public void onLongClick(int position) {
                remove(position);
            }
        });
        //Для обработки свайпа на элементе
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                remove(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);
    }

    private void remove(int position) {
        Note note = adapter.getNotes().get(position);
        //viewModel.deltetNote(note);
        dao.deletNote(note);
    }

    public void onClickAddNote(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        getData();
        super.onResume();
    }
    /*private void getData() {
        LiveData<List<Note>> notesFromDB = viewModel.getNotes();
        notesFromDB.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notesFromLiveData) {
                adapter.setNotes(notesFromLiveData);
            }
        });
    }*/
    private void getData() {

        LiveData<List<Note>> notesFromDB = dao.getAllNotes();
        notesFromDB.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notesFromLiveData) {
                adapter.setNotes(notesFromLiveData);
            }
        });
    }

    /* Удаленно в видео (7 ААС)
    private void getData() {
        notes.clear();
        Пример который показывает как вывести заметки только за определнные день недели(понедельник)
        String selection = NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK + " == ?";
        String[] selectionArgs = new String[]{"1"};

        //selection && selectionArgs для того чтобы выводит только определенные заметки
        // в нашем случае только те у которых приоритет меньше 2 (тоесть самый высокий)
        String selection = NotesContract.NotesEntry.COLUMN_PRIORITY + " < ?";
        String[] selectionArgs = new String[]{"2"};
        Cursor cursor = database.query(NotesContract.NotesEntry.TABLE_NAME, null,selection,selectionArgs,null,null, NotesContract.NotesEntry.COLUMN_PRIORITY);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry._ID));
            String title = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DESCRIPTION));
            int dayOfWeek = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK));
            int priority = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_PRIORITY));
            Note note = new Note(id, title, description, dayOfWeek, priority);
            notes.add(note);
        }
        cursor.close();
    }*/
}