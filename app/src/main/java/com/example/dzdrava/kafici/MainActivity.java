package com.example.dzdrava.kafici;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Kafic> kaficList;
    RecyclerView rv;
    // Dinko db adapter kao varijabla članica kako bi bio dostupan svim metodama
    private DBAdapter db = null;

    Button btn;
    CheckBox kava;
    CheckBox cijena;
    CheckBox pusacki;
    CheckBox nepusacki;
    CheckBox wifi;
    CheckBox uticnice;
    CheckBox psi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Dinko inicijalizacija adaptera
        db = new DBAdapter(this);
        // učitavanje baze s interneta
        //new DownloadTextTask().execute("https://raw.githubusercontent.com/dzdrav/Android-kafici/master/kafici.csv");
        new DownloadTextTask().execute("http://web.studenti.math.pmf.unizg.hr/~dzdrava/kafici.csv");

        setContentView(R.layout.recyclerview_activity);

        //testna baza
        /*
        db.open();
        db.umetniKafic("Miss Donut","Harambašićeva 31,10000 Zagreb",1,5,5,3,4,3,4,4,3,3,1,1,-1,1,-1);
        db.umetniKafic("Finjak","Vlaška 78,10000 Zagreb",1,4,4,4,4,2,4,5,3,5,1,1,1,1,-1);
        db.umetniKafic("Procaffe","Tkalčićeva 54, 10000 Zagreb",4,1.75,2.75,3,4.25,2.75,3.75,5,4.75,4.75,1,1,1,1,1);
        db.umetniKafic("Potter","Sesvetska 1, 10000 Zagreb",4,2,3.5,2.25,3.75,3.75,3.75,4.25,4,4.5,1,1,1,1,1);
        db.umetniKafic("Tesla Smart Bar","Horvaćanska cesta 146a, 10000 Zagreb",1,3,2,3,3,3,4,4,5,5,0,1,1,-1,1);
        // obrisati ovo
        //Log.d("Kafica u bazi ima", String.valueOf(db.dohvatiSveKafice().getCount()));
        db.close();
        */

        btn = (Button) findViewById(R.id.button2);
        kava=(CheckBox) findViewById(R.id.checkBoxKava);
        cijena=(CheckBox) findViewById(R.id.checkBoxCijena);
        pusacki=(CheckBox) findViewById(R.id.checkBoxPusacki);
        nepusacki=(CheckBox) findViewById(R.id.checkBoxNepusacki);
        wifi=(CheckBox) findViewById(R.id.checkBoxWifi);
        uticnice=(CheckBox) findViewById(R.id.checkBoxuticnice);
        psi=(CheckBox) findViewById(R.id.checkBoxPsi);

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


    private int GetImage(Context c, String ImageName) {
        //getIdentifier daje lokalni id resursa
        //getResources daje resurs
        int other=c.getResources().getIdentifier("coffee", "drawable", c.getPackageName());
        int id= c.getResources().getIdentifier(ImageName, "drawable", c.getPackageName());
        if(id>0){
            return id;
        }
        else return other;
    }
    private void initializeData(){
        kaficList = new ArrayList<>();
        //iz baze, dobavljaj redak po redak, i za svaki redak
        //kaficList.add(new Kafic(id,ime,adresa,R.drawable.coffee,brojOcjena,cijena,kava);
        //ako ima slike, umjesto coffee ime slike

        // dohvaćanje kafića iz baze i pospremanje u listu

        db.open();
        Cursor c = db.dohvatiSveKafice();
        if (c.moveToFirst())
        {
            do {
                int slika=GetImage(this,"k"+c.getString(0));
                Log.d(""+slika,"inicijalizacija liste");
                kaficList.add(new Kafic(Integer.parseInt(c.getString(0)),c.getString(1), c.getString(2),slika,Integer.parseInt(c.getString(3)),Double.parseDouble(c.getString(8)),Double.parseDouble(c.getString(9))));
            } while (c.moveToNext());
        }
        db.close();
    }

    // Dinko čitanje teksta iz datoteke s neta
    private ArrayList<String> DownloadText(String URL)
    {
        ArrayList<String> ListaKafica = new ArrayList<String>();
        //Dinko
        try {
            // Create a URL for the desired destination
            URL url = new URL(URL); //My text file location
            //First open the connection
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(60000); // timing out in a minute

            BufferedReader in2 = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String str;
            while ((str = in2.readLine()) != null) {
                ListaKafica.add(str);
            }
            in2.close();
        } catch (Exception e) {
            Log.d("MyTag",e.toString());
        }
        return ListaKafica;
    }

    // pomoćna klasa za dohvaćanje teksta
    private class DownloadTextTask extends AsyncTask<String, Void, ArrayList<String>> {
        protected ArrayList<String> doInBackground(String... urls) {
            return DownloadText(urls[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            db.open();
            Cursor c = db.dohvatiSveKafice();
            // umećemo retke samo ako je tablica prazna
            // TODO maknuti uvjet ako postoji druga provjera za ponovni unos kafića u OnCreate()
            if ((c ==  null) || (c.getCount() == 0) ) {
            Long id;
                for (String redak : result){
                    String[] kategorije = redak.split(";");
                    if (!kategorije[0].equals("ID")) {
                        try{
                            id = db.umetniKafic(kategorije[1], kategorije[2], Integer.parseInt(kategorije[3])
                                    , Double.parseDouble(kategorije[4])
                                    , Double.parseDouble(kategorije[5])
                                    , Double.parseDouble(kategorije[6])
                                    , Double.parseDouble(kategorije[7])
                                    , Double.parseDouble(kategorije[8])
                                    , Double.parseDouble(kategorije[9])
                                    , Double.parseDouble(kategorije[10])
                                    , Double.parseDouble(kategorije[11])
                                    , Double.parseDouble(kategorije[12])
                                    , Integer.parseInt(kategorije[13])
                                    , Integer.parseInt(kategorije[14])
                                    , Integer.parseInt(kategorije[15])
                                    , Integer.parseInt(kategorije[16])
                                    , Integer.parseInt(kategorije[17])
                            );
                            Log.d("Umetnut kafic ID", id.toString());
                        }
                        catch (Exception e){
                            Log.e("Insert greška",e.toString());
                        }
                    }
                }
            }
            db.close();
        }
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(kaficList);
        rv.setAdapter(adapter);
    }

    public void filter(View view){

        Toast.makeText(this, R.string.filter_filtrirano, Toast.LENGTH_LONG).show();
        boolean isCheckedKava = kava.isChecked();
        boolean isCheckedCijena = cijena.isChecked();
        boolean isCheckedPusacki=pusacki.isChecked();
        boolean isCheckedNepusacki = nepusacki.isChecked();
        boolean isCheckedWifi = wifi.isChecked();
        boolean isCheckedPsi = psi.isChecked();
        boolean isCheckedUticnice = uticnice.isChecked();
        kaficList=getList(isCheckedKava,isCheckedCijena,isCheckedPusacki,isCheckedNepusacki,isCheckedWifi,isCheckedPsi,isCheckedUticnice);
        initializeAdapter();
    }

    //provjera za pojedinu tvrdnju
    //je li vrijednost ==1
    private boolean provjeriFilterBool(boolean filter,int vrijednost){
        if(filter && vrijednost==1) return true;
        else if (filter && vrijednost!=1) return false;
        else return true;

    }

    //provjera za pojedinu kvalitetu usluge
    //je li vrijednost izmedu lowBound i upBound (ukljuceno)
    private boolean provjeriFilterDouble(boolean filter,double vrijednost,int lowBound,int upBound){
        if (filter && (vrijednost>upBound || vrijednost<lowBound)) return false;
        else return true;

    }


    //funkcija prima sve checkboxove
    List<Kafic> getList(boolean kava,boolean cijena,boolean pusacki, boolean nepusacki, boolean wifi,boolean psi, boolean uticnice){
        //prolaskom po bazi, za svaki redak gledamo odgovara li zahtjevima iz checkboxova
        //ako odgovara, dodajemo ga u listu newList, na način opisan u initializeData()
        //u tablici baze, pozicije filtara su sljedece:
        //kava:9,cijena:8,nepusacki:13,pusenje:14,wifi:15,psi:16,uticnice:17

        List<Kafic> newList = new ArrayList<>();
        db.open();
        Cursor c = db.dohvatiSveKafice();
        if (c.moveToFirst())
        {
            do {
                if(provjeriFilterBool(pusacki,Integer.parseInt(c.getString(14))) && provjeriFilterBool(nepusacki,Integer.parseInt(c.getString(13))) &&
                        provjeriFilterBool(wifi,Integer.parseInt(c.getString(15))) && provjeriFilterBool(psi,Integer.parseInt(c.getString(16))) &&
                        provjeriFilterBool(uticnice,Integer.parseInt(c.getString(17)))
                        && provjeriFilterDouble(kava,Double.parseDouble(c.getString(9)),4,5) && provjeriFilterDouble(cijena,Double.parseDouble(c.getString(8)),1,2))
                {
                    int slika=GetImage(this,"k"+c.getString(0));
                    newList.add(new Kafic(Integer.parseInt(c.getString(0)),c.getString(1), c.getString(2), slika,Integer.parseInt(c.getString(3)),Double.parseDouble(c.getString(8)),Double.parseDouble(c.getString(9))));

                }
            } while (c.moveToNext());
        }
        db.close();
        return newList;
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

        /*FragmentManager fragmentManager = getSupportFragmentManager();
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
        fragmentTransaction.commit();*/

        return true;
    }
}
