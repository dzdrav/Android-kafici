package com.example.dzdrava.kafici;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by pemarti on 2/27/18.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.KaficViewHolder>{

    public static class KaficViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public static final String EXTRA_MESSAGE = "com.example.dzdrava.kafici.MESSAGE";

        CardView cv;
        TextView kaficName;
        TextView kaficAdress;
        ImageView kaficPhoto;
        int kaficId=0;

        KaficViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            kaficName = (TextView)itemView.findViewById(R.id.kafic_name);
            kaficAdress = (TextView)itemView.findViewById(R.id.kafic_adress);
            kaficPhoto = (ImageView)itemView.findViewById(R.id.kafic_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //Log.d("klik", "onClick " + view.toString() + " " + "adadas");
            sendMessage(view,kaficId);
        }

        public void sendMessage(View view,int id) {
            Context context = view.getContext();
            Intent intent = new Intent(context, DisplayActivity.class);
            String message =Integer.toString(id);
            intent.putExtra(EXTRA_MESSAGE, message);
            context.startActivity(intent);
        }
    }
    List<Kafic> kaficList;
    //private final View.OnClickListener mOnClickListener = new MyOnClickListener();

    RVAdapter(List<Kafic> kaficList){
        this.kaficList = kaficList;
    }

    @Override
    public int getItemCount() {
        return kaficList.size();
    }

    @Override
    public KaficViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        KaficViewHolder pvh = new KaficViewHolder(v);
        //viewGroup.setOnClickListener(mOnClickListener);
        Log.d("stvori", "onCreate " + Integer.toString(i));
        return pvh;
    }

    @Override
    public void onBindViewHolder(KaficViewHolder kaficViewHolder, int i) {
        Log.d("stvori", "onBind " + Integer.toString(i));
        kaficViewHolder.kaficName.setText(kaficList.get(i).name);
        kaficViewHolder.kaficAdress.setText(kaficList.get(i).adress);
        kaficViewHolder.kaficPhoto.setImageResource(kaficList.get(i).photoId);
        kaficViewHolder.kaficId=kaficList.get(i).dbId;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
