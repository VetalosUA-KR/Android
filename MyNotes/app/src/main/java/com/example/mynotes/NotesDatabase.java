package com.example.mynotes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Для того чтобы во всем приложении работать только с одной базой данных
//поэтому объект NoteDatabase должен быть один и тотже
//поэтому используем singleton
@Database(entities = Note.class, version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NotesDao notesDao();

    public static final String DB_NAME = "notes2.db";
    private static NotesDatabase database;
    private static final Object LOCK = new Object();
    //LOCK добавляем для того чтобы при запросе с разных потоков, не создавалось
    //2 разные баззы данных
    public static NotesDatabase getInstance(Context context) {
        //synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, NotesDatabase.class, DB_NAME)
                        .allowMainThreadQueries()//Никогда не использовать в реальнмо приложении!!!
                        .build();
            }
        //}
        return database;
    }


}
//Все работа с базами должна происходит в потоке отличном от главного потоке
