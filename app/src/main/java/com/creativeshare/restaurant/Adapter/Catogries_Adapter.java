package com.creativeshare.restaurant.Adapter;


import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeshare.restaurant.Actifities.MainActivity;
import com.creativeshare.restaurant.Model.Catogry_Model_Slide;
import com.creativeshare.restaurant.R;
import com.creativeshare.restaurant.Tags.Tags;
import com.creativeshare.restaurant.preferences.Preferences;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Catogries_Adapter extends RecyclerView.Adapter<Catogries_Adapter.Eyas_Holder> {
    List<Catogry_Model_Slide.Cat> list;
    Context context;
    MainActivity activity;
    Preferences preferences;
String current_lang,title;
    public Catogries_Adapter(List<Catogry_Model_Slide.Cat> list, Context context) {
        this.list = list;
        this.context = context;
        activity = (MainActivity) context;
        preferences=Preferences.getInstance();
        current_lang=preferences.getlang(activity);
    }

    @Override
    public Eyas_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_catogry, viewGroup, false);
        Eyas_Holder eas = new Eyas_Holder(v);
        return eas;
    }

    @Override
    public void onBindViewHolder(@NonNull final Eyas_Holder viewHolder, int i) {
        final Catogry_Model_Slide.Cat model = list.get(i);
        if(current_lang.equals("ar")){
        viewHolder.txt.setText(model.getAr_title());}
        else {
            viewHolder.txt.setText(model.getEn_title());
        }
        //Toast.makeText(activity,Tags.IMAGE_URL_catogry+model.getCat_image(),Toast.LENGTH_LONG).show();
        Picasso.with(context).load(Tags.IMAGE_URL_catogry+model.getCat_image()).centerCrop().fit().into(viewHolder.im);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (current_lang.equals("ar")){
                  list.get(viewHolder.getLayoutPosition()).getAr_title();
              }
              else {
                  list.get(viewHolder.getLayoutPosition()).getEn_title();

              }
                activity.DisplayFragmentSub_Catogry(title,list.get(viewHolder.getLayoutPosition()).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Eyas_Holder extends RecyclerView.ViewHolder {
        TextView txt;
        ImageView im;

        public Eyas_Holder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt2);
            im = itemView.findViewById(R.id.img1);
        }
    }
}
