package com.creativeshare.restaurant.Adapter;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeshare.restaurant.Actifities.MainActivity;
import com.creativeshare.restaurant.Model.Sub_Catogry_Model_Slide;
import com.creativeshare.restaurant.R;
import com.creativeshare.restaurant.Tags.Tags;
import com.creativeshare.restaurant.preferences.Preferences;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Sub_Catogries_Adapter extends RecyclerView.Adapter<Sub_Catogries_Adapter.Eyas_Holder>{
    List<Sub_Catogry_Model_Slide.Sub> list;
    Context context;
    MainActivity activity;
    Preferences preferences;
    String current_lang;
    public Sub_Catogries_Adapter(List<Sub_Catogry_Model_Slide.Sub> list, Context context){
        this.list=list;
        this.context=context;
        activity=(MainActivity)context;
        preferences=Preferences.getInstance();
        current_lang=preferences.getlang(activity);


    }
    @Override
    public Eyas_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_sub_catogry,viewGroup,false);
        Eyas_Holder eas=new Eyas_Holder(v);
        return eas;
    }

    @Override
    public void onBindViewHolder(@NonNull final Eyas_Holder viewHolder, int i) {
        Sub_Catogry_Model_Slide.Sub model = list.get(i);
        if(current_lang.equals("ar")){
            viewHolder.txt_name.setText(model.getAr_title());}
        else {
            viewHolder.txt_name.setText(model.getEn_title());
        }
        viewHolder.txt_price.setText(model.getPrice()+"");
        Picasso.with(context).load(Tags.IMAGE_URL_subcatogry+model.getImage()).fit().into(viewHolder.im);

        viewHolder.detials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.DisplayFragmnetDetials(list.get(viewHolder.getLayoutPosition()));    }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class Eyas_Holder extends RecyclerView.ViewHolder {
        TextView txt_name,txt_price,detials;
        ImageView im;
        CheckBox checkBox;

        public Eyas_Holder(@NonNull View itemView) {
            super(itemView);
            txt_name=(TextView)itemView.findViewById(R.id.txt_name);
            txt_price=itemView.findViewById(R.id.txt_price);
            detials=itemView.findViewById(R.id.details);
            im=itemView.findViewById(R.id.img1);
            checkBox=itemView.findViewById(R.id.checkbox);

        }


    }
}
