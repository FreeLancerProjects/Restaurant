package com.creativeshare.restaurant.Actifities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.creativeshare.restaurant.Fragments.Fragment_Home;
import com.creativeshare.restaurant.R;

public class MainActivity extends AppCompatActivity {
private Fragment_Home fragment_home;
private int fragment_count=0;
FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){
            fragmentManager = this.getSupportFragmentManager();
            DisplayFragmentHome();

        }
    }
    public void DisplayFragmentHome() {

        fragment_count += 1;

        if (fragment_home == null) {
            fragment_home = Fragment_Home.newInstance();
        }

        if (fragment_home.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_home).commit();
        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_home, "fragment_home").addToBackStack("fragment_home").commit();
        }

    }
}
