package com.armados.app.epraxeis;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = Config.TABLE_NAME_FAVORITES,
        primaryKeys = {"type","uid"})
public class FavoriteEntity {
    public static final String ORGANIZATION = "organization";
    public static final String SIGNER = "signer";
    public static final String UNIT = "unit";

    @NonNull
    private String type;

    @NonNull
    private String uid;

    @NonNull
     private String label;

    @ColumnInfo(name = "comment")
    private String comment;

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    @NonNull
    public String getLabel() {
        return label;
    }

    public void setLabel(@NonNull String label) {
        this.label = label;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}