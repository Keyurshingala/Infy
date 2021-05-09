package com.example.myapplication.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Postdb.class}, version = 1)
public abstract class PostDatabase extends RoomDatabase {

    public abstract PostDao postDao();

    private static PostDatabase INSTANCE;

    public static PostDatabase getPostDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PostDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PostDatabase.class, "Post.db").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
