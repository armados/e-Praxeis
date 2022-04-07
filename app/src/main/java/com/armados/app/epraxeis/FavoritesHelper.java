package com.armados.app.epraxeis;

import android.content.Context;

public class FavoritesHelper {

    public static void delete(Context context, String type, String uid) {
        Database.getInstance(context).getFavoriteDao().deleteUid(type,uid);
    }

    public static void insert(Context context, String type, String uid, String label, String description) {
        FavoriteEntity entity = new FavoriteEntity();
        entity.setType(type);
        entity.setUid(uid);
        entity.setLabel(label);
        entity.setComment(description);

        Database.getInstance(context).getFavoriteDao().insert(entity);
    }

    public static boolean isFavorite(Context context, String type, String uid) {
        return Database.getInstance(context).getFavoriteDao().isFavorite(type, uid);
    }

}
