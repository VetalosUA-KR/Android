package com.example.mynotes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

//Data Access Object - объект для доступа к базе данных
//по сути это интерфейс для работы с базой данных
@Dao
public interface NotesDao {

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAllNotes();

    @Insert
    void insertNote(Note note);

    @Query("SELECT * FROM notes WHERE id = :noteId")
    Note getNoteById(int noteId);


    @Update
    void updateNote(Note note);

    @Delete
    void deletNote(Note note);

    @Query("DELETE FROM notes")
    void deleteAllNote();

}
