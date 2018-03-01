package com.example.dzdrava.kafici;

import java.util.ArrayList;

/**
 * Created by pemarti on 2/27/18.
 */
class Kafic {
    int dbId;
    String name;
    String adress;
    int photoId;
    int brojOcjena;
    double cijena;
    double kava;

    int wifi;
    int psi;


    Kafic(int dbId, String name, String adress, int photoId,int brojOcjena,double cijena,double kava) {
        this.dbId = dbId;
        this.name = name;
        this.adress = adress;
        this.photoId = photoId;
        this.brojOcjena=brojOcjena;
        this.cijena=cijena;
        this.kava=kava;
    }

}
