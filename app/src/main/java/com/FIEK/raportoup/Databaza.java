package com.FIEK.raportoup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databaza extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "Databaza";

    // Database Version
    private static final int DATABASE_VERSION = 6;

    // Database Name
    private static final String DATABASE_NAME = "raportoupDB";

    //
    public static final String PerdoruesitTable = "perdoruesit";

    //
    public static final String RaportiRiTable = "raporti_i_ri";

    //
    public Databaza(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strQuery = "create table " + PerdoruesitTable + " (" +
                Perdoruesi.ID + " integer primary key autoincrement," +
                Perdoruesi.Email + " text," +
                Perdoruesi.Username + " text not null," +
                Perdoruesi.Password + " text not null " +
                ")";

        String strQuery1 = "create table " + RaportiRiTable + " (" +
                Raporti_i_ri.ID + " integer primary key autoincrement, " +
                Raporti_i_ri.Username + " text not null," +
                Raporti_i_ri.Koment + " text, " +
                Raporti_i_ri.Kategorite + " text, " +
                Raporti_i_ri.SelectedImage + " blob, " +
                Raporti_i_ri.Koha + " DEFAULT CURRENT_TIMESTAMP, " +
                Raporti_i_ri.Adresa + " text " +
                ")";

        db.execSQL(strQuery);
        db.execSQL(strQuery1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + PerdoruesitTable);
        db.execSQL("drop table if exists " + RaportiRiTable);
        onCreate(db);
    }
}