package com.FIEK.raportoup.databaza;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.FIEK.raportoup.aktivitetet.FaqjaPare;

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
                RaportiRi.ID + " integer primary key autoincrement, " +
                RaportiRi.Username + " text not null," +
                RaportiRi.Koment + " text, " +
                RaportiRi.Kategorite + " text, " +
                RaportiRi.SelectedImage + " blob, " +
                RaportiRi.Koha + " DEFAULT CURRENT_TIMESTAMP, " +
                RaportiRi.Adresa + " text " +
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

    public Cursor readAllData() {
        String query = " SELECT * FROM " + RaportiRiTable + " WHERE username ='" + FaqjaPare.strExtras + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}