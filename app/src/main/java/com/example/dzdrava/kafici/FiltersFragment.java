package com.example.dzdrava.kafici;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * Created by pemarti on 2/28/18.
 */

public class FiltersFragment extends Fragment implements View.OnClickListener {
    View view;
    Button btn;
    CheckBox chb1;
    CheckBox chb2;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //---Inflate the layout for this fragment---
        View rootView = inflater.inflate(
                R.layout.fragment, container, false);
        btn = (Button) rootView.findViewById(R.id.button2);
        chb1 = (CheckBox) rootView.findViewById(R.id.checkBox1);
        chb2 = (CheckBox) rootView.findViewById(R.id.checkBox2);
        btn.setOnClickListener(this);
        return rootView;
    }
    @Override
    public void onClick(View view) {
        //Toast.makeText(getContext(), "You clicked on filters", Toast.LENGTH_LONG).show();

        boolean isChecked = chb1.isChecked();
        boolean isChecked2 = chb2.isChecked();
        //MainActivity.kaficList=MainActivity.getList(isChecked,isChecked2);

    }
}

