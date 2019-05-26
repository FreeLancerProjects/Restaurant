package com.creativeshare.restaurant.Fragments;


import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.creativeshare.restaurant.Actifities.MainActivity;
import com.creativeshare.restaurant.R;


public class Fragment_Home extends Fragment {
    private AHBottomNavigation bottomNavigationView;
    MainActivity activity;
    public static Fragment_Home newInstance() {
        return new Fragment_Home();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    View view= inflater.inflate(R.layout.fragment_home, container, false);
    intitview(view);
    setUpBottomNav();
    return view;
    }

    private void intitview(View view) {
        bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        activity=(MainActivity)getActivity();
    }

    private void setUpBottomNav() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home, R.drawable.ic_home, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.Cart, R.drawable.ic_cart, R.color.colorPrimary);
        bottomNavigationView.addItem(item1);
        bottomNavigationView.addItem(item2);

        bottomNavigationView.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigationView.setInactiveColor(Color.parseColor("#747474"));
        bottomNavigationView.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        bottomNavigationView.setForceTint(true);
        bottomNavigationView.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigationView.setColored(false);

        bottomNavigationView.setCurrentItem(0);


        bottomNavigationView.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                       // activity.DisplayFragmentMain();
                        break;
                    case 1:
                       // activity.DisplayFragmentOrders();
                        break;



                }
                return false;
            }
        });
    }

    public void UpdateAHBottomNavigationPosition(int pos) {

        if (pos == 0)
        {
            //tv_title.setText(getString(R.string.home));
        }else if (pos == 1)
        {
           // tv_title.setText(getString(R.string.needs));

        }
        bottomNavigationView.setSelectedBackgroundVisible(true);

        bottomNavigationView.setCurrentItem(pos, false);
    }
}
