package com.example.dzdrava.kafici;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

/**
 * Created by pemarti on 2/28/18.
 */

public class MyOnClickListener implements View.OnClickListener {
    RecyclerView mRecyclerView;

    @Override
    public void onClick(View view) {
        mRecyclerView=(RecyclerView)view.findViewById(R.id.rv);
        int itemPosition = mRecyclerView.getChildLayoutPosition(view);
        //String item = mList.get(itemPosition);
        //Toast.makeText(mContext, item, Toast.LENGTH_LONG).show();
    }
}
