package com.FIEK.raportoup.databaza;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.FIEK.raportoup.aktivitetet.FaqjaPare;
import com.FIEK.raportoup.utilities.Hash;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class Databaza extends SQLiteOpenHelper {

    private static final String LOG = "Databaza";
    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "raportoupDB";
    public static final String PerdoruesitTable = "perdoruesit";
    public static final String RaportiRiTable = "raporti_i_ri";

    public Databaza(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strQuery = "create table " + PerdoruesitTable + " (" +
                Perdoruesi.ID + " integer unique," +
                Perdoruesi.Email + " text unique not null," +
                Perdoruesi.Username + " text primary key," +
                Perdoruesi.Password + " text not null, " +
                Perdoruesi.Admin + " boolean default 0 " +
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

    public Cursor readAllDataAdmin() {
        String query = " SELECT * FROM " + RaportiRiTable;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void shtoAdmin() {
        String pass = Hash.md5("A123456");
        String query = " INSERT INTO " + PerdoruesitTable + " (email, username, password, admin) VALUES('admin@gmail.com', 'admin', '" + pass + "', 1) ";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    public List<PieEntry> stats() {
        List<PieEntry> al = new ArrayList<PieEntry>();
        SQLiteDatabase dbs = this.getReadableDatabase();

        Cursor cursorS = dbs.rawQuery(" SELECT kategorite, COUNT(*) as countS FROM " +
                RaportiRiTable + " GROUP BY " + RaportiRi.Kategorite, null);
        if (cursorS.moveToFirst()) {
            do {
                al.add(new PieEntry(cursorS.getInt(1), cursorS.getString(0)));
            }
            while (cursorS.moveToNext());
        }
        return al;
    }
}