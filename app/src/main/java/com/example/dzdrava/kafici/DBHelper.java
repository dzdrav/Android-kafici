package com.example.dzdrava.kafici;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dzdrava on 2/28/18.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "KaficiDB";
    public static final String KAFICI_TABLE_NAME = "Kafici";
    public static final String KAFICI_COLUMN_ID = "_Id";
    public static final String KAFICI_COLUMN_NAZIV = "Naziv";
    public static final String KAFICI_COLUMN_ADRESA = "Adresa";
    //public static final Integer DATABASE_VERSION = 1;
    /*
    public static final String KAFICI_COLUMN_EMAIL = "email";
    public static final String KAFICI_COLUMN_STREET = "street";
    public static final String KAFICI_COLUMN_CITY = "place";
    public static final String KAFICI_COLUMN_PHONE = "phone";
    */
    /*
    static final String DATABASE_CREATE_KAFICI = "create table " + KAFICI_TABLE_NAME +
            // ovdje ukloniti AUTOINCREMENT ako stvara probleme
            "(" + KAFICI_COLUMN_ID + " integer primary key autoincrement" +
            ", " + KAFICI_COLUMN_NAZIV + " text" +
            ", " + KAFICI_COLUMN_ADRESA + " text)";
            */
    static final String DATABASE_CREATE_KAFICI =
            "CREATE TABLE " + KaficContract.KaficEntry.TABLE_NAME + " (" +
                    KaficContract.KaficEntry._ID + " INTEGER PRIMARY KEY," +
                    KaficContract.KaficEntry.COLUMN_NAME_NAZIV + " TEXT," +
                    KaficContract.KaficEntry.COLUMN_NAME_ADRESA + " TEXT)";
    static final String DATABASE_DROP = "DROP TABLE IF EXISTS " + KaficContract.KaficEntry.TABLE_NAME;


    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DATABASE_CREATE_KAFICI);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DATABASE_DROP);
        onCreate(db);
    }
/*
    public boolean umetniKafic(String naziv, String adresa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KAFICI_COLUMN_NAZIV, naziv);
        contentValues.put(KAFICI_COLUMN_ADRESA, adresa);
        db.insert(KAFICI_TABLE_NAME, null, contentValues);
        //db.close();
        return true;
    }

    public Cursor dohvatiKaficPoId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + KAFICI_TABLE_NAME + " where " + KAFICI_COLUMN_ID + "=" + id + "", null);
        return res;
    }

    public ArrayList<String> dohvatiSveKafice() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + KAFICI_TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(KAFICI_COLUMN_NAZIV)));
            res.moveToNext();
        }
        return array_list;
    }
*/
}

