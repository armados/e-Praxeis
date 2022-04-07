package com.armados.app.epraxeis;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(tableName = Config.TABLE_NAME_STORE_DICTIONARY,
        primaryKeys = {"dictionary","uid"})
public class DictionaryEntity implements PairUidLabel {

    @NonNull
    private String dictionary;

    @NonNull
    private String uid;

    @NonNull
    private String label;

    @NonNull
    public String getDictionary() {
        return dictionary;
    }

    public void setDictionary(@NonNull String dictionary) {
        this.dictionary = dictionary;
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
}