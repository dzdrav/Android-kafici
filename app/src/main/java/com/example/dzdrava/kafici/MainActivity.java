package com.example.dzdrava.kafici;

import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Kafic> kaficList;
    RecyclerView rv;
    // Dinko db adapter kao varijabla članica kako bi bio dostupan svim metodama
    private DBAdapter db = null;

    //LinearLayoutManager llm=new LinearLayoutManager(this.getBaseContext());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Dinko inicijalizacija adaptera
        db = new DBAdapter(this);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_activity);

        rv=(RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        CreateMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return MenuChoice(item);
    }

        // Dinko - funkcija za ispis kontakata (Karaga)
    /*
    public void DisplayContact(Cursor c)
    {
        Toast.makeText(this,
                "id: " + c.getString(0) + "\n" +
                        "Name: " + c.getString(1) + "\n" +
                        "Email:  " + c.getString(2),
                Toast.LENGTH_LONG).show();
    }*/
    // TODO promijeniti odredište spremanja ovih podataka, radi testa stavljamo ih u Listu varijablu članicu
    public void PopulateData()
    {
        db.open();
        Cursor c = db.dohvatiSveKafice();
        if (c.moveToFirst())
        {
            do {
                Toast.makeText(this, "Novi unos:\nid:" + c.getString(0) + "\n" + "Naziv: " + c.getString(1) + "\n" + "Adresa" + c.getString(2),Toast.LENGTH_SHORT);
                kaficList.add(new Kafic(Integer.parseInt( c.getString(0) ), c.getString(1), c.getString(2), R.drawable.miss_donut));
            } while(c.moveToNext());
        }
        db.close();
    }
    // --Dinko

    private void initializeData(){
        kaficList = new ArrayList<>();
        //33
        kaficList.add(new Kafic(33,"Miss Donut", "Harambasiceva 32a", R.drawable.miss_donut));
        //19
        kaficList.add(new Kafic(19,"Finjak", "Vlaska 78", R.drawable.finjak));
        //42
        kaficList.add(new Kafic(42,"Potter caffe", "Sesvetska 1", R.drawable.potter));
        //43
        kaficList.add(new Kafic(43,"Procaffe", "Tkalciceva 54", R.drawable.procaffe));
        //53
        kaficList.add(new Kafic(53,"Tesla Smart Bar", "Horvacanska cesta 146a", R.drawable.tesla_smart_bar));

            // Dinko: inicijalizacija baze
        // test - umetanje 2 kafica
        db.open();
        long id2;
        id2 = db.umetniKafic("Albatros", "Ljubljanica",1,4,5,4,5,4,4,5,5,5);
        id2 = db.umetniKafic("Bulldog", "Bogovićeva ul. 6",1,1,3,5,5,5,4,4,5,5);
        db.close();

        // dohvaćanje kafića iz baze i pospremanje u listu
        PopulateData();
        //--get all contacts---
        /*
        db.open();
        Cursor c = db.getAllContacts();
        if (c.moveToFirst())
        {
            do {
                DisplayContact(c);
            } while (c.moveToNext());
        }
        db.close();
        */
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(kaficList);
        rv.setAdapter(adapter);
    }

    private void CreateMenu(Menu menu)
    {
        menu.setQwertyMode(true);
        MenuItem mnu1 = menu.add(0, 0, 0, "Filteri");
        {
            mnu1.setAlphabeticShortcut('f');
            mnu1.setIcon(R.mipmap.ic_launcher);
        }

    }
    //druga pomocna fukcija
    private boolean MenuChoice(MenuItem item)
    {
        Toast.makeText(this, "You clicked on filters",
                Toast.LENGTH_LONG).show();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();


            //---landscape mode---
            FiltersFragment fragment1 = new FiltersFragment();
            // android.R.id.content refers to the content
            // view of the activity
            fragmentTransaction.replace(
                    android.R.id.content, fragment1);

        //---add to the back stack---
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        return true;
    }
}
