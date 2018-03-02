package com.example.dzdrava.kafici;

/**
 * Created by pemarti on 3/1/18.
 */
/*
public static final String TABLE_NAME = "Kafici";
        public static final String COLUMN_ID = "_Id";
        public static final String COLUMN_NAZIV = "Naziv";
        public static final String COLUMN_ADRESA = "Adresa";
        public static final String COLUMN_BROJOCJENA = "BrojOcjena";
        public static final String COLUMN_PROSJECNAGUZVA = "ProsjecnaGuzva";
        public static final String COLUMN_OSVJETLJENJE = "Osvjetljenje";
        public static final String COLUMN_RAZINABUKE = "RazinaBuke";
        public static final String COLUMN_LJUBAZNOSTOSOBLJA = "LjubaznostOsoblja";
        public static final String COLUMN_CIJENE = "Cijene";
        public static final String COLUMN_KVALITETAKAVE = "KvalitetaKave";
        public static final String COLUMN_UREDNOSTWC = "UrednostWC";
        public static final String COLUMN_UDOBNOSTSTOLICA = "UdobnostStolica";
        public static final String COLUMN_UKUPNAATMOSFERA = "UkupnaAtmosfera";
        // odavde nadalje su tzv. tvrdnje tipa int (-1 ako nije def)
        public static final String COLUMN_PROSTORZANEPUSACE = "ProstorZaNepusace";
        public static final String COLUMN_DOZVOLJENOPUSENJE = "DozvoljenoPusenje";
        public static final String COLUMN_WIFI = "Wifi";
        public static final String COLUMN_DOZVOLJENIPSI = "DozvoljeniPsi";
        public static final String COLUMN_UTICNICE = "Uticnice";
 */
class KaficDetaljno {
    int dbId;
    String name;
    String adress;
    int brojOcjena;
    double guzva;
    double osvjetljenje;
    double buka;
    double osoblje;
    double cijena;
    double kava;
    double wc;
    double stolice;
    double atmosfera;

    int nepusaci;
    int pusenje;
    int wifi;
    int psi;
    int uticnice;

    int photoId;

    KaficDetaljno(int dbId, String name, String adress, int brojOcjena, double guzva, double osvjetljenje,double buka,
    double osoblje, double cijena,double kava,double wc, double stolice,double atmosfera, int nepusaci, int pusenje, int wifi,
                  int psi,int uticnice,int photoId) {
        this.dbId = dbId;
        this.name = name;
        this.adress = adress;
        this.photoId = photoId;
        this.brojOcjena=brojOcjena;
        this.cijena=cijena;
        this.kava=kava;
        this.guzva=guzva;
        this.osvjetljenje=osvjetljenje;
        this.buka=buka;
        this.osoblje=osoblje;
        this.wc=wc;
        this.stolice=stolice;
        this.atmosfera=atmosfera;
        this.nepusaci=nepusaci;
        this.pusenje=pusenje;
        this.wifi=wifi;
        this.psi=psi;
        this.uticnice=uticnice;
    }
}
