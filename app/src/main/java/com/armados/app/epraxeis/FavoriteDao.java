package com.armados.app.epraxeis;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoriteEntity data);

    @Query("SELECT * FROM " + Config.TABLE_NAME_FAVORITES + " WHERE type=:type ORDER BY label ASC")
    List<FavoriteEntity> getAll(String type);

    @Query("SELECT * FROM " + Config.TABLE_NAME_FAVORITES + " WHERE type=:type AND uid=:uid")
    FavoriteEntity getEntry(String type, String uid);

    @Query("SELECT EXISTS (SELECT 1 FROM " + Config.TABLE_NAME_FAVORITES + " WHERE type=:type AND uid=:uid)")
    boolean isFavorite(String type, String uid);

    @Query("DELETE FROM " + Config.TABLE_NAME_FAVORITES + " WHERE type=:type AND uid=:uid")
    void deleteUid(String type, String uid);
}