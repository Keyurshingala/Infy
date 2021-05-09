package com.example.myapplication.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Userdb.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDuo();

    private static UserDatabase INSTANCE;

    public static UserDatabase getUserDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class,"User.db").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
