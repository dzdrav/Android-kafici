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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Kafic> kaficList;
    RecyclerView rv;

    //LinearLayoutManager llm=new LinearLayoutManager(this.getBaseContext());


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
