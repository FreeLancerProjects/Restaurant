package com.creativeshare.restaurant.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.creativeshare.restaurant.Actifities.MainActivity;
import com.creativeshare.restaurant.Adapter.Catogries_Adapter;
import com.creativeshare.restaurant.Adapter.Sub_Catogries_Adapter;
import com.creativeshare.restaurant.Model.Catogry_Model_Slide;
import com.creativeshare.restaurant.R;
import com.creativeshare.restaurant.preferences.Preferences;

import java.util.ArrayList;


public class Fragment_Sub_Catogry extends Fragment {
    private RecyclerView recyclerView;
    private ImageView back;
    private Catogry_Model_Slide catogry_model_slide;
    private Preferences preferences;
    private int image[];
    private String name[],current_lang;
    private ArrayList<Catogry_Model_Slide> catogry_model_slides;
    private Sub_Catogries_Adapter catogries_adapter;
    private MainActivity activity;
    public static Fragment_Sub_Catogry newInstance() {
      return new Fragment_Sub_Catogry();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_sub__catogry, container, false);
       ititview(view);
       return view;
    }
    private void ititview(View view) {
        activity=(MainActivity)getActivity();
        image= new int[]{R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4};
        name=new String[]{"food1","food2","food3","food4"};
        recyclerView=view.findViewById(R.id.catogry);
        back=view.findViewById(R.id.back);
        preferences=Preferences.getInstance();
        current_lang=preferences.getlang(activity);
        if(current_lang.equals("ar")){
            back.setRotation(180);
        }
        catogry_model_slides=new ArrayList<>();
        for(int i=0;i<4;i++){
            catogry_model_slide=new Catogry_Model_Slide();
            catogry_model_slide.setImage(image[i]);
            catogry_model_slide.setName(name[i]);
            catogry_model_slides.add(catogry_model_slide);
        }
        catogries_adapter=new Sub_Catogries_Adapter(catogry_model_slides,activity);
        recyclerView.setLayoutManager(new GridLayoutManager(activity,2));
        recyclerView.setAdapter(catogries_adapter);
back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        activity.Back();
    }
});


    }


    public void change_rec() {
        for(int i=0;i<catogry_model_slides.size();i++){
            View view=(View)recyclerView.getChildAt(i);
            CheckBox checkBox=view.findViewById(R.id.checkbox);
            if(checkBox.getVisibility()==View.GONE){
                checkBox.setVisibility(View.VISIBLE);
            }
            else{
                checkBox.setVisibility(View.GONE);
                checkBox.setChecked(false);
            }
        }
    }
}
