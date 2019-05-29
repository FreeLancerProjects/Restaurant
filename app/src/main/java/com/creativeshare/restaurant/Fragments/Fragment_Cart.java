package com.creativeshare.restaurant.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creativeshare.restaurant.Actifities.MainActivity;
import com.creativeshare.restaurant.Adapter.Sub_Catogries_Adapter;
import com.creativeshare.restaurant.Model.Catogry_Model_Slide;
import com.creativeshare.restaurant.R;

import java.util.ArrayList;


public class Fragment_Cart extends Fragment {
    private RecyclerView recyclerView;
    private Catogry_Model_Slide catogry_model_slide;
    private int image[];
    private String name[];
    private ArrayList<Catogry_Model_Slide> catogry_model_slides;
    private Sub_Catogries_Adapter catogries_adapter;
    private MainActivity activity;
    public static Fragment_Cart newInstance() {
       return new Fragment_Cart();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        ititview(view);
        return view;
    }

    private void ititview(View view) {
        activity=(MainActivity)getActivity();
        image= new int[]{R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4};
        name=new String[]{"food1","food2","food3","food4"};
        recyclerView=view.findViewById(R.id.catogry);

        catogry_model_slides=new ArrayList<>();
        for(int i=0;i<4;i++){
            catogry_model_slide=new Catogry_Model_Slide();
            catogry_model_slide.setImage(image[i]);
            catogry_model_slide.setName(name[i]);
            catogry_model_slides.add(catogry_model_slide);
        }
        catogries_adapter=new Sub_Catogries_Adapter(catogry_model_slides,activity);
        recyclerView.setLayoutManager(new GridLayoutManager(activity,4));
        recyclerView.setAdapter(catogries_adapter);



    }

}
