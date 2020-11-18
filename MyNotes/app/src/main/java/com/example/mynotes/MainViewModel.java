package com.example.mynotes;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static NotesDatabase database;
    private LiveData<List<Note>> notes;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = NotesDatabase.getInstance(getApplication());
        notes = database.notesDao().getAllNotes();
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    public void insertNote(Note note) {
        new InsertTask().execute(note);
    }

    public void updateNote(Note note) {
        new UpdateTask().execute(note);
        Log.i("qwe", "ITS WORK!");
    }

    public void deltetNote(Note note) {
        new DeleteTask().execute(note);
    }

    /// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private static class UpdateTask extends AsyncTask<Note, Void, Void> {

        @Override
        protected Void doInBackground(Note... notes) {
            database.notesDao().updateNote(notes[0]);

            Log.i("qwe", "ITS WORK! 2"+notes[0].getTitle());
            return null;
        }
    }

    private static class InsertTask extends AsyncTask<Note, Void, Void> {

        @Override
        protected Void doInBackground(Note... notes) {
            if(notes != null && notes.length > 0) {
                database.notesDao().insertNote(notes[0]);
            }
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Note, Void, Void> {

        @Override
        protected Void doInBackground(Note... notes) {
            if(notes != null && notes.length > 0) {
                database.notesDao().deletNote(notes[0]);
            }
            return null;
        }
    }

    private static class DeleteAllTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... notes) {
            database.notesDao().deleteAllNote();
            return null;
        }
    }

    public void deltetAllNote() {
        new DeleteAllTask().execute();
    }
}
