package com.example.mymovies.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class, FavouriteMovie.class}, version = 3, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    //Используем паттерн singelton
    private static MovieDatabase database;
    private static final String DB_NAME = "movies.db";
    //блок синхронизации чтобы не было проблем доступа из разных потоков
    private static final Object LOCK = new Object();

    public static MovieDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if(database == null) {
                //fallbackToDestructiveMigration - для того что бы при изменения версии базы (version = 2) происходило автоматическое изменение
                database = Room.databaseBuilder(context, MovieDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
            }
        }

        return database;
    }

    public abstract MovieDao movieDao();
}
