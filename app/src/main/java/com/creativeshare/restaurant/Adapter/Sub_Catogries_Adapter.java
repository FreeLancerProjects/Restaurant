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
import com.creativeshare.restaurant.Model.Catogry_Model_Slide;
import com.creativeshare.restaurant.R;
import com.creativeshare.restaurant.preferences.Preferences;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Sub_Catogries_Adapter extends RecyclerView.Adapter<Sub_Catogries_Adapter.Eyas_Holder>{
List<Catogry_Model_Slide> list;
Context context;
MainActivity activity;
Preferences preferences;
String current_lang;
  public Sub_Catogries_Adapter(List<Catogry_Model_Slide> list, Context context){
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
        Catogry_Model_Slide model = list.get(i);
        viewHolder.txt.setText(model.getName());
        viewHolder.txt2.setText("5555");
        Picasso.with(context).load(model.getImage()).fit().into(viewHolder.im);
viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        viewHolder.checkBox.setChecked(true);
        activity.Changecheckboxvisibilty();


    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class Eyas_Holder extends RecyclerView.ViewHolder {
TextView txt,txt2;
ImageView im;
CheckBox checkBox;

        public Eyas_Holder(@NonNull View itemView) {
            super(itemView);
            txt2=(TextView)itemView.findViewById(R.id.txt2);
            txt=itemView.findViewById(R.id.txt);
            im=itemView.findViewById(R.id.img1);
            checkBox=itemView.findViewById(R.id.checkbox);
            if(current_lang.equals("ar")){
                txt.setGravity(Gravity.START);
                txt2.setGravity(Gravity.END);
            }
        }


    }
}
