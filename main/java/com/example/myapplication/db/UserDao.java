package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void saveRecord(Userdb userdb);

    @Query("select*from Userdb")
    List<Userdb> readRecord();

    @Query("delete from Userdb where email = :email")
    void deleteRecord(String  email);

    @Update
    void updateRecord(Userdb userdb);
}

