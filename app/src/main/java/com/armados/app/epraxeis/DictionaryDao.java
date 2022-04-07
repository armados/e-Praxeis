package com.armados.app.epraxeis;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DictionaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DictionaryEntity data);

    @Query("SELECT * FROM " + Config.TABLE_NAME_STORE_DICTIONARY + " WHERE dictionary=:dictionary AND uid=:uid")
    DictionaryEntity getEntry(String dictionary, String uid);

    @Query("SELECT * FROM " + Config.TABLE_NAME_STORE_DICTIONARY + " WHERE dictionary=:dictionary ORDER BY label ASC")
    List<DictionaryEntity> getAllEntries(String dictionary);
}