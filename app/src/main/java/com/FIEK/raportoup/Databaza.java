package com.FIEK.raportoup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Databaza extends SQLiteOpenHelper {

    public static final String PerdoruesitTable = "perdoruesit";

    public Databaza(@Nullable Context context) {
        super(context, "raportoupDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strQuery = "create table " + PerdoruesitTable + " (" +
                Perdoruesi.ID + " integer primary key autoincrement," +
                Perdoruesi.Email + " text," +
                Perdoruesi.Username + " text not null," +
                Perdoruesi.Password + " text not null" +
                ")";

        db.execSQL(strQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
