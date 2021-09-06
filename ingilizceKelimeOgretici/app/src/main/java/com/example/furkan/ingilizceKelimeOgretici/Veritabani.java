package com.example.furkan.ingilizceKelimeOgretici;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by furka on 29/04/16.
 */
public class Veritabani extends SQLiteOpenHelper {
    private static final String VERITABANI = "Kelimeler";
    private static final int SURUM = 1;

    public Veritabani(Context con) {
        super(con, VERITABANI, null, SURUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS tblKelimeler(id INTEGER PRIMARY KEY AUTOINCREMENT,ingKel TEXT,turKel TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST tblKelimeler");
        onCreate(db);
    }
}