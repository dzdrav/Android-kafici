package com.example.dzdrava.kafici;


import android.provider.BaseColumns;

/**
 * Created by dzdrava on 2/28/18.
 */

public class KaficContract implements BaseColumns {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private KaficContract() {}

    /* Inner class that defines the table contents */
    public static class KaficEntry implements BaseColumns {
        public static final String TABLE_NAME = "Kafici";
        public static final String COLUMN_NAME_NAZIV = "Naziv";
        public static final String COLUMN_NAME_ADRESA = "Adresa";
    }

}
