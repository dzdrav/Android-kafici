package com.example.dzdrava.kafici;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity implements OnMapReadyCallback {

    private List<KaficDetaljno> kaficList;
    //kafic nije klase Kafic nego KaficDetaljno
    KaficDetaljno kafic;
    TextView name;
    TextView adress;
    TextView cijena;
    TextView kava;
    TextView buka;
    TextView guzva;
    TextView osvjetljenje;
    TextView osoblje;
    TextView wc;
    TextView atmosfera;
    TextView stolice;
    ImageView cover;
    private DBAdapter db = null;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location = getLocationFromAddress(this,kafic.adress);
        if (location != null){
            googleMap.addMarker(new MarkerOptions().position(location)
                    .title(kafic.name));
            googleMap.getMaxZoomLevel();
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15));
        }
    }
    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }
        return p1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        db = new DBAdapter(this);

        //umjesto initializeData, dobavljamo kafic iz baze s id-em "message"

        //initializeData();


        Log.d("pocetak displayActivity","onCreate");

        Intent intent = getIntent();
        String message = intent.getStringExtra(RVAdapter.KaficViewHolder.EXTRA_MESSAGE);

        //iz baze, dobavi redak s id-em "message" i u strukturu KaficDetaljno spremi potrebne stupce
        int id=Integer.parseInt(message);
        kafic=getKafic(id);

        name=(TextView)findViewById(R.id.name);
        adress=(TextView)findViewById(R.id.adress);
        cover=(ImageView)findViewById(R.id.cover_photo);
        cijena=(TextView) findViewById(R.id.cijena);
        kava=(TextView) findViewById(R.id.kava);
        buka=(TextView) findViewById(R.id.buka);
        guzva=(TextView) findViewById(R.id.guzva);
        osoblje=(TextView) findViewById(R.id.osoblje);
        osvjetljenje=(TextView) findViewById(R.id.osvjetljenje);

        name.setText(kafic.name);
        adress.setText(kafic.adress);
        cijena.setText(kafic.cijena+"/5");
        kava.setText(kafic.kava+"/5");
        buka.setText(kafic.buka+"/5");
        guzva.setText(kafic.guzva+"/5");
        osvjetljenje.setText(kafic.osvjetljenje+"/5");
        osoblje.setText(kafic.osoblje+"/5");
        cover.setImageResource(kafic.photoId);

        Log.d("prije mape","onCreate");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    KaficDetaljno getKafic(int dbId) {
        KaficDetaljno newKafic;
        db.open();
        Cursor c=db.dohvatiKafic(dbId);
        int slika=GetImage(this,"k"+c.getString(0));
        newKafic=new KaficDetaljno(
                Integer.parseInt(c.getString(0)),c.getString(1),c.getString(2),Integer.parseInt(c.getString(3)),
                Double.parseDouble(c.getString(4)),Double.parseDouble(c.getString(7)),Double.parseDouble(c.getString(6)),
                Double.parseDouble(c.getString(7)),Double.parseDouble(c.getString(8)),Double.parseDouble(c.getString(9))
                ,Double.parseDouble(c.getString(10)),Double.parseDouble(c.getString(11)),Double.parseDouble(c.getString(12))
                ,Integer.parseInt(c.getString(13)),Integer.parseInt(c.getString(14)),Integer.parseInt(c.getString(15)),Integer.parseInt(c.getString(16)),Integer.parseInt(c.getString(17)),
                slika
        );
        db.close();
        return newKafic;
    }

    //nepotrebno
    /*private void initializeData(){
        kaficList = new ArrayList<>();
        //k33
        kaficList.add(new Kafic(k33,"Miss Donut", "Harambasiceva 32a", R.drawable.miss_donut,2,1.5,4.5));
        //19
        kaficList.add(new Kafic(19,"Finjak", "Vlaska 78", R.drawable.k19,1,2,4));
        //42
        kaficList.add(new Kafic(42,"Potter caffe", "Sesvetska 1", R.drawable.k42,4,3.75,3.75));
        //43
        kaficList.add(new Kafic(43,"Procaffe", "Tkalciceva 54", R.drawable.k43,4,2.75,3.75));
        //53
        kaficList.add(new Kafic(53,"Tesla Smart Bar", "Horvacanska cesta 146a", R.drawable.k53,1,4,5));
    }*/

}
