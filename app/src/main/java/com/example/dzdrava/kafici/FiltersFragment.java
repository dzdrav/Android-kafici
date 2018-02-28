package com.example.dzdrava.kafici;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by pemarti on 2/28/18.
 */

public class FiltersFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //---Inflate the layout for this fragment---
        return inflater.inflate(
                R.layout.fragment, container, false);
    }
}
