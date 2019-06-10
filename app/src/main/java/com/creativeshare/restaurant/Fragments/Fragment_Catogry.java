package com.creativeshare.restaurant.Fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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
private ProgressBar progressBar;
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
        recyclerView=view.findViewById(R.id.catogry);
        progressBar = (ProgressBar) view.findViewById(R.id.progBar2);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        recyclerView.setItemViewCacheSize(25);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        getAllCatogries();

    }

    private void getAllCatogries() {
        Api.getService().getcateogries().enqueue(new Callback<Catogry_Model_Slide>() {
            @Override
            public void onResponse(Call<Catogry_Model_Slide> call, Response<Catogry_Model_Slide> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    catogry_model_slides = response.body().getCat();
                    catogries_adapter = new Catogries_Adapter(catogry_model_slides, activity);
                    recyclerView.setLayoutManager(new GridLayoutManager(activity, 4));
                    recyclerView.setAdapter(catogries_adapter);
                }

                else {
                    Log.e("code",response.errorBody()+""+response.code());
                }
            }
            @Override
            public void onFailure(Call<Catogry_Model_Slide> call, Throwable t) {
                progressBar.setVisibility(View.GONE);

                Log.e("code",t.getMessage());

            }
        });
    }

}
