package com.example.dzdrava.kafici;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by dzdrava on 3/1/18.
 */

public class DBAdapter {
    //static final String KEY_ROWID = "_id";
    //static final String KEY_NAME = "name";
    //static final String KEY_EMAIL = "email";
    static final String TAG = "DBAdapter";

    static final String DATABASE_NAME = "Kafici";
    static final String DATABASE_TABLE = "glavna";
    static final int DATABASE_VERSION = 4;

    // DMS stringovi za tablicu kafici
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + KaficContract.KaficEntry.TABLE_NAME + " (" +
            KaficContract.KaficEntry.COLUMN_ID + " INTEGER PRIMARY KEY autoincrement," +
            KaficContract.KaficEntry.COLUMN_NAZIV + " TEXT," +
            KaficContract.KaficEntry.COLUMN_ADRESA + " TEXT," +
            KaficContract.KaficEntry.COLUMN_BROJOCJENA + " INTEGER," +
            KaficContract.KaficEntry.COLUMN_PROSJECNAGUZVA + " REAL," +
            KaficContract.KaficEntry.COLUMN_OSVJETLJENJE + " REAL," +
            KaficContract.KaficEntry.COLUMN_RAZINABUKE + " REAL," +
            KaficContract.KaficEntry.COLUMN_LJUBAZNOSTOSOBLJA + " REAL," +
            KaficContract.KaficEntry.COLUMN_CIJENE + " REAL," +
            KaficContract.KaficEntry.COLUMN_KVALITETAKAVE + " REAL," +
            KaficContract.KaficEntry.COLUMN_UREDNOSTWC + " REAL," +
            KaficContract.KaficEntry.COLUMN_UDOBNOSTSTOLICA + " REAL," +
            KaficContract.KaficEntry.COLUMN_UKUPNAATMOSFERA + " REAL," +
            KaficContract.KaficEntry.COLUMN_PROSTORZANEPUSACE + " INTEGER," +
            KaficContract.KaficEntry.COLUMN_DOZVOLJENOPUSENJE + " INTEGER," +
            KaficContract.KaficEntry.COLUMN_WIFI + " INTEGER," +
            KaficContract.KaficEntry.COLUMN_DOZVOLJENIPSI + " INTEGER," +
            KaficContract.KaficEntry.COLUMN_UTICNICE + " INTEGER)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + KaficContract.KaficEntry.TABLE_NAME;
    //--

    //static final String DATABASE_CREATE =
      //      "create table contacts (_id integer primary key autoincrement, "
        //            + "name text not null, email text not null);";

    static final String DATABASE_CREATE=SQL_CREATE_ENTRIES;

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    // database helper pomoćna klasa
    // s metodama za kreiranje baze
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            /*// TEST: dodajemo isti segment koda za dodatnu tablicu kafici
            try {
                db.execSQL(SQL_CREATE_ENTRIES);
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            // TEST: dodajemo isti segment koda za dodatnu tablicu kafici
            //Log.w(TAG, "Upgrading db from" + oldVersion + "to" + newVersion );
            db.execSQL(SQL_DELETE_ENTRIES);
            //-- do ovdje
            Log.w(TAG, "Upgrading db from" + oldVersion + "to"
                    + newVersion );
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

        // metode za manipulaciju podacima u bazi
    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    // Dinko: testne metode za manipulaciju kaficima u bazi
    //--umetanje novog kafica po parametrima (TODO samo 2)--
    public long umetniKafic(String naziv, String adresa, int brOcjena, double guzva
            , double osvjetljenje, double buka, double ljubaznost, double cijene, double kvaliteta, double urednost
            , double udobnost, double atmosfera, int nepusaci, int pusenje, int wifi, int psi, int uticnice){
        ContentValues cv = new ContentValues();
        cv.put(KaficContract.KaficEntry.COLUMN_NAZIV, naziv);
        cv.put(KaficContract.KaficEntry.COLUMN_ADRESA, adresa);
        cv.put(KaficContract.KaficEntry.COLUMN_BROJOCJENA, brOcjena);
        cv.put(KaficContract.KaficEntry.COLUMN_PROSJECNAGUZVA, guzva);
        cv.put(KaficContract.KaficEntry.COLUMN_OSVJETLJENJE, osvjetljenje);
        cv.put(KaficContract.KaficEntry.COLUMN_RAZINABUKE, buka);
        cv.put(KaficContract.KaficEntry.COLUMN_LJUBAZNOSTOSOBLJA, ljubaznost);
        cv.put(KaficContract.KaficEntry.COLUMN_CIJENE, cijene);
        cv.put(KaficContract.KaficEntry.COLUMN_KVALITETAKAVE, kvaliteta);
        cv.put(KaficContract.KaficEntry.COLUMN_UREDNOSTWC, urednost);
        cv.put(KaficContract.KaficEntry.COLUMN_UDOBNOSTSTOLICA, udobnost);
        cv.put(KaficContract.KaficEntry.COLUMN_UKUPNAATMOSFERA, atmosfera);
        cv.put(KaficContract.KaficEntry.COLUMN_PROSTORZANEPUSACE, nepusaci);
        cv.put(KaficContract.KaficEntry.COLUMN_DOZVOLJENOPUSENJE, pusenje);
        cv.put(KaficContract.KaficEntry.COLUMN_WIFI, wifi);
        cv.put(KaficContract.KaficEntry.COLUMN_DOZVOLJENIPSI, psi);
        cv.put(KaficContract.KaficEntry.COLUMN_UTICNICE, uticnice);
        return db.insert(KaficContract.KaficEntry.TABLE_NAME, null, cv);
    }

    public Cursor dohvatiSveKafice(){
        return db.query(KaficContract.KaficEntry.TABLE_NAME
            ,new String[] {KaficContract.KaficEntry.COLUMN_ID
            ,KaficContract.KaficEntry.COLUMN_NAZIV
            ,KaficContract.KaficEntry.COLUMN_ADRESA
            ,KaficContract.KaficEntry.COLUMN_BROJOCJENA
            ,KaficContract.KaficEntry.COLUMN_PROSJECNAGUZVA
            ,KaficContract.KaficEntry.COLUMN_OSVJETLJENJE
            ,KaficContract.KaficEntry.COLUMN_RAZINABUKE
            ,KaficContract.KaficEntry.COLUMN_LJUBAZNOSTOSOBLJA
            ,KaficContract.KaficEntry.COLUMN_CIJENE
            ,KaficContract.KaficEntry.COLUMN_KVALITETAKAVE
            ,KaficContract.KaficEntry.COLUMN_UREDNOSTWC
            ,KaficContract.KaficEntry.COLUMN_UDOBNOSTSTOLICA
            ,KaficContract.KaficEntry.COLUMN_UKUPNAATMOSFERA
            ,KaficContract.KaficEntry.COLUMN_PROSTORZANEPUSACE
            ,KaficContract.KaficEntry.COLUMN_DOZVOLJENOPUSENJE
            ,KaficContract.KaficEntry.COLUMN_WIFI
            ,KaficContract.KaficEntry.COLUMN_DOZVOLJENIPSI
            ,KaficContract.KaficEntry.COLUMN_UTICNICE}, null, null, null, null, null);
    }

    public Cursor dohvatiKafic(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, KaficContract.KaficEntry.TABLE_NAME, new String[] {
                                KaficContract.KaficEntry.COLUMN_ID
                                ,KaficContract.KaficEntry.COLUMN_NAZIV
                                ,KaficContract.KaficEntry.COLUMN_ADRESA
                                ,KaficContract.KaficEntry.COLUMN_BROJOCJENA
                                ,KaficContract.KaficEntry.COLUMN_PROSJECNAGUZVA
                                ,KaficContract.KaficEntry.COLUMN_OSVJETLJENJE
                                ,KaficContract.KaficEntry.COLUMN_RAZINABUKE
                                ,KaficContract.KaficEntry.COLUMN_LJUBAZNOSTOSOBLJA
                                ,KaficContract.KaficEntry.COLUMN_CIJENE
                                ,KaficContract.KaficEntry.COLUMN_KVALITETAKAVE
                                ,KaficContract.KaficEntry.COLUMN_UREDNOSTWC
                                ,KaficContract.KaficEntry.COLUMN_UDOBNOSTSTOLICA
                                ,KaficContract.KaficEntry.COLUMN_UKUPNAATMOSFERA
                                ,KaficContract.KaficEntry.COLUMN_PROSTORZANEPUSACE
                                ,KaficContract.KaficEntry.COLUMN_DOZVOLJENOPUSENJE
                                ,KaficContract.KaficEntry.COLUMN_WIFI
                                ,KaficContract.KaficEntry.COLUMN_DOZVOLJENIPSI
                                ,KaficContract.KaficEntry.COLUMN_UTICNICE
                        }, KaficContract.KaficEntry.COLUMN_ID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public boolean obrisiKafic(long rowId)
    {
        return db.delete(KaficContract.KaficEntry.TABLE_NAME, KaficContract.KaficEntry.COLUMN_NAZIV + "=" + rowId, null) > 0;
    }

    public boolean osvjeziKafic(long rowId, String naziv, String adresa, int brOcjena, double guzva
            , double osvjetljenje, double buka, double ljubaznost, double cijene, double kvaliteta, double urednost
            , double udobnost, double atmosfera, int nepusaci, int pusenje, int wifi, int psi, int uticnice)
    {
        ContentValues args = new ContentValues();

        args.put(KaficContract.KaficEntry.COLUMN_NAZIV, naziv);
        args.put(KaficContract.KaficEntry.COLUMN_ADRESA, adresa);
        args.put(KaficContract.KaficEntry.COLUMN_BROJOCJENA, brOcjena);
        args.put(KaficContract.KaficEntry.COLUMN_PROSJECNAGUZVA, guzva);
        args.put(KaficContract.KaficEntry.COLUMN_OSVJETLJENJE, osvjetljenje);
        args.put(KaficContract.KaficEntry.COLUMN_RAZINABUKE, buka);
        args.put(KaficContract.KaficEntry.COLUMN_LJUBAZNOSTOSOBLJA, ljubaznost);
        args.put(KaficContract.KaficEntry.COLUMN_CIJENE, cijene);
        args.put(KaficContract.KaficEntry.COLUMN_KVALITETAKAVE, kvaliteta);
        args.put(KaficContract.KaficEntry.COLUMN_UREDNOSTWC, urednost);
        args.put(KaficContract.KaficEntry.COLUMN_UDOBNOSTSTOLICA, udobnost);
        args.put(KaficContract.KaficEntry.COLUMN_UKUPNAATMOSFERA, atmosfera);
        args.put(KaficContract.KaficEntry.COLUMN_PROSTORZANEPUSACE, nepusaci);
        args.put(KaficContract.KaficEntry.COLUMN_DOZVOLJENOPUSENJE, pusenje);
        args.put(KaficContract.KaficEntry.COLUMN_WIFI, wifi);
        args.put(KaficContract.KaficEntry.COLUMN_DOZVOLJENIPSI, psi);
        args.put(KaficContract.KaficEntry.COLUMN_UTICNICE, uticnice);
        return db.update(KaficContract.KaficEntry.TABLE_NAME, args, KaficContract.KaficEntry.COLUMN_NAZIV + "=" + rowId, null) > 0;
    }
    //-- do ovdje

    //DONE
    //---insert a contact into the database---
    /*public long insertContact(String name, String email)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_EMAIL, email);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }*/

    //DONE
    //---deletes a particular contact---
    /*public boolean deleteContact(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }*/

    //DONE
    //---retrieves all the contacts---
    /*public Cursor getAllContacts()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME,
                KEY_EMAIL}, null, null, null, null, null);
    }*/

    //DONE
    //---retrieves a particular contact---
    /*public Cursor getContact(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                                KEY_ROWID,
                                KEY_NAME,
                                KEY_EMAIL
                }, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }*/

    //DONE
    //---updates a contact---
    /*public boolean updateContact(long rowId, String name, String email)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_EMAIL, email);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }*/

}
