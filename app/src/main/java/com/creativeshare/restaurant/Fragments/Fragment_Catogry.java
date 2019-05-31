package com.creativeshare.restaurant.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creativeshare.restaurant.Actifities.MainActivity;
import com.creativeshare.restaurant.Adapter.Catogries_Adapter;
import com.creativeshare.restaurant.Model.Catogry_Model_Slide;
import com.creativeshare.restaurant.R;
import com.creativeshare.restaurant.remote.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Catogry extends Fragment {
private RecyclerView recyclerView;
private int image[];
private String name[];
private List<Catogry_Model_Slide.Cat> catogry_model_slides;
private Catogries_Adapter catogries_adapter;
private MainActivity activity;
    public static Fragment_Catogry newInstance() {
return new Fragment_Catogry();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_catogry, container, false);
      ititview(view);
      return view;
    }

    private void ititview(View view) {
        activity=(MainActivity)getActivity();
        image= new int[]{R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4};
        recyclerView=view.findViewById(R.id.catogry);

getAllCatogries();

    }

    private void getAllCatogries() {
        Api.getService().getcateogries().enqueue(new Callback<Catogry_Model_Slide>() {
            @Override
            public void onResponse(Call<Catogry_Model_Slide> call, Response<Catogry_Model_Slide> response) {
                if (response.isSuccessful()) {
                    catogry_model_slides = response.body().getCat();
                    catogries_adapter = new Catogries_Adapter(catogry_model_slides, activity);
                    recyclerView.setLayoutManager(new GridLayoutManager(activity, 1));
                    recyclerView.setAdapter(catogries_adapter);
                }
                else {
                    Log.e("code",response.errorBody()+""+response.code());
                }
            }
            @Override
            public void onFailure(Call<Catogry_Model_Slide> call, Throwable t) {
                Log.e("code",t.getMessage());

            }
        });
    }

}
