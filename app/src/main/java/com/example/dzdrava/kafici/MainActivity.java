package com.example.dzdrava.kafici;

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

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Kafic> kaficList;
    RecyclerView rv;

    Button btn;
    CheckBox chb1;
    CheckBox chb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_activity);

        btn = (Button) findViewById(R.id.button2);
        chb1 = (CheckBox) findViewById(R.id.checkBox1);
        chb2 = (CheckBox) findViewById(R.id.checkBox2);
        //btn.setOnClickListener(this);

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


    private void initializeData(){
        kaficList = new ArrayList<>();
        //iz baze, dobavljaj redak po redak, i za svaki redak
        //kaficList.add(new Kafic(id,ime,adresa,R.drawable.coffee,brojOcjena,cijena,kava);
        //ako ima slike, umjesto coffee ime slike

        //33
        kaficList.add(new Kafic(33,"Miss Donut", "Harambasiceva 32a", R.drawable.miss_donut,2,1.5,4.5));
        //19
        kaficList.add(new Kafic(19,"Finjak", "Vlaska 78", R.drawable.finjak,1,2,4));
        //42
        kaficList.add(new Kafic(42,"Potter caffe", "Sesvetska 1", R.drawable.potter,4,3.75,3.75));
        //43
        kaficList.add(new Kafic(43,"Procaffe", "Tkalciceva 54", R.drawable.procaffe,4,2.75,3.75));
        //53
        kaficList.add(new Kafic(53,"Tesla Smart Bar", "Horvacanska cesta 146a", R.drawable.tesla_smart_bar,1,4,5));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(kaficList);
        rv.setAdapter(adapter);
    }

    public void filter(View view){

        Toast.makeText(this, "You clicked on filters", Toast.LENGTH_LONG).show();
        boolean isChecked = chb1.isChecked();
        boolean isChecked2 = chb2.isChecked();
        kaficList=getList(isChecked,isChecked2);
        initializeAdapter();
    }

    //funkcija prima sve checkboxove
    List<Kafic> getList(boolean f1,boolean f2){
        //prolaskom po bazi, za svaki redak gledamo odgovara li zahtjevima iz checkboxova
        //ako odgovara, dodajemo ga u listu newList, na način opisan u initializeData()

        List<Kafic> newList = new ArrayList<>();
        if(f1 & !f2) newList.add(new Kafic(33,"Miss Donut", "Harambasiceva 32a", R.drawable.miss_donut,2,1.5,4.5));
        else if(f1 & f2) {
            //33
            newList.add(new Kafic(33,"Miss Donut", "Harambasiceva 32a", R.drawable.miss_donut,2,1.5,4.5));
            //19
            newList.add(new Kafic(19,"Finjak", "Vlaska 78", R.drawable.finjak,1,2,4));
            //42
            newList.add(new Kafic(42,"Potter caffe", "Sesvetska 1", R.drawable.potter,4,3.75,3.75));
        }
        else if(f2){
            //19
            newList.add(new Kafic(19,"Finjak", "Vlaska 78", R.drawable.finjak,1,2,4));
            //42
            newList.add(new Kafic(42,"Potter caffe", "Sesvetska 1", R.drawable.potter,4,3.75,3.75));
        }
        else newList.add(new Kafic(53,"Tesla Smart Bar", "Horvacanska cesta 146a", R.drawable.tesla_smart_bar,1,4,5));

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
