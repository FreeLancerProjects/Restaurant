package com.creativeshare.restaurant.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.creativeshare.restaurant.R;


public class Fragment_Catogry extends Fragment {

    public static Fragment_Catogry newInstance() {
return new Fragment_Catogry();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_catogry, container, false);
      return view;
    }

}
