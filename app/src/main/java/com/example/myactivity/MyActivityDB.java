package com.example.myactivity;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class}, version = 2, exportSchema = false)
public abstract class MyActivityDB extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static volatile MyActivityDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MyActivityDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyActivityDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyActivityDB.class, "myactivity_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
