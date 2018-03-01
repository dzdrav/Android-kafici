package com.example.dzdrava.kafici;

import android.provider.BaseColumns;
import android.provider.ContactsContract;

/**
 * Created by dzdrava on 3/1/18.
 */

public final class KaficContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private KaficContract() {}

    /* Inner class that defines the table contents */
    public static class KaficEntry implements BaseColumns{
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
    }

}
