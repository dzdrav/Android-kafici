package com.example.dzdrava.kafici;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewDebug;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
// TODO pogledati kod s predavanja
// kreirati klasu Adapter i UNUTAR nje staviti DBHelper

public class MainActivity extends AppCompatActivity {

    private List<Person> persons;
    RecyclerView rv;

    // Dinko
    private DBHelper mydb;

    LinearLayoutManager llm=new LinearLayoutManager(this.getBaseContext());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_activity);

        rv=(RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();

        //Dinko
        mydb = new DBHelper(this);
    }
    private void initializeData(){
        persons = new ArrayList<>();

        // proba s podacima iz baze, obrisati po potrebi
        //SQLiteDatabase db = mydb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KaficContract.KaficEntry.COLUMN_NAME_NAZIV, "Alfredo");
        values.put(KaficContract.KaficEntry.COLUMN_NAME_ADRESA, "Draskoviceva 56");
        long newrowid = db.insert(KaficContract.KaficEntry.TABLE_NAME, null, values);

        //mydb.umetniKafic("Kafic 1", "Ulica 64");
        //mydb.umetniKafic("Kafic 2", "Ulica 128");

        //Cursor resultSet =  mydb.dohvatiKaficPoId(1);
        //resultSet.moveToFirst();
        //String naziv = resultSet.getString(resultSet.getColumnIndex(DBHelper.KAFICI_COLUMN_NAZIV));
        //String adresa = resultSet.getString(resultSet.getColumnIndex(DBHelper.KAFICI_COLUMN_ADRESA));
        //persons.add(new Person(naziv, adresa, R.drawable.emma));
        // -- kraj --
        persons.add(new Person("Emma Wilson", "23 years old", R.drawable.emma));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.lavery));
        persons.add(new Person("Lillie Watts", "35 years old", R.drawable.lillie));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }


}
