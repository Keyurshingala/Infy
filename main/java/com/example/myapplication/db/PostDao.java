package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PostDao {
    @Insert
    void saveRecord(Postdb postdb);

    @Query("select*from Postdb")
    List<Postdb> readRecord();

    @Query("delete from Postdb where id = :id")
    void deleteRecord(int id);

    @Update
    void updateRecord(Postdb postdb);
}
