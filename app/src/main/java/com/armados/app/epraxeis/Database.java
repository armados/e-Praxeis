package com.armados.app.epraxeis;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(
        entities = {
                FavoriteEntity.class,
                DictionaryEntity.class
        },
        version = 1,
        exportSchema = false
)
public abstract class Database extends RoomDatabase {
    private static Database db;

    public abstract DictionaryDao getDictionaryDao();

    public abstract FavoriteDao getFavoriteDao();

    public static Database getInstance(Context context) {
        if (db == null)
            db = buildDatabaseInstance(context);

        return db;
    }

    private static Database buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(
                context,
                Database.class,
                Configuration.DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
    }
}