package com.creativeshare.restaurant.Adapter;


import android.content.Context;
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
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Catogries_Adapter extends RecyclerView.Adapter<Catogries_Adapter.Eyas_Holder>{
List<Catogry_Model_Slide> list;
Context context;
MainActivity activity;
  public Catogries_Adapter(List<Catogry_Model_Slide> list, Context context){
this.list=list;
this.context=context;
activity=(MainActivity)context;

   }
    @Override
    public Eyas_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_catogry,viewGroup,false);
        Eyas_Holder eas=new Eyas_Holder(v);
        return eas;
    }

    @Override
    public void onBindViewHolder(@NonNull final Eyas_Holder viewHolder, int i) {
        Catogry_Model_Slide model = list.get(i);
        viewHolder.txt.setText(model.getName());
        Picasso.with(context).load(model.getImage()).fit().into(viewHolder.im);
viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        activity.DisplayFragmentSub_Catogry();
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
            txt=(TextView)itemView.findViewById(R.id.txt2);
            im=itemView.findViewById(R.id.img1);

        }


    }
}
