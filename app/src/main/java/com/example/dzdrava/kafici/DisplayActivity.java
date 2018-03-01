package com.example.dzdrava.kafici;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private List<Kafic> kaficList;
    //kafic nije klase Kafic nego KaficDetaljno
    Kafic kafic;
    TextView name;
    TextView adress;
    ImageView cover;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location = getLocationFromAddress(this,kafic.adress);
        googleMap.addMarker(new MarkerOptions().position(location)
                .title(kafic.name));
        googleMap.getMaxZoomLevel();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,15));

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

        //umjesto initializeData, dobavljamo kafic iz baze s id-em "message"

        initializeData();

        Intent intent = getIntent();
        String message = intent.getStringExtra(RVAdapter.KaficViewHolder.EXTRA_MESSAGE);

        //iz baze, dobavi redak s id-em "message" i u strukturu KaficDetaljno spremi potrebne stupce
        kafic=findKafic(Integer.parseInt(message));

        name=(TextView)findViewById(R.id.name);
        adress=(TextView)findViewById(R.id.adress);
        cover=(ImageView)findViewById(R.id.cover_photo);

        name.setText(kafic.name);
        adress.setText(kafic.adress);
        cover.setImageResource(kafic.photoId);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //nepotrebno
    Kafic findKafic(int dbId) {
        for(Kafic kafic : kaficList) {
            if (kafic.dbId==dbId)
                return kafic;
        }
        return null;
    }

    //nepotrebno
    private void initializeData(){
        kaficList = new ArrayList<>();
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

}
