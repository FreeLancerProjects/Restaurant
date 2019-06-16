package com.creativeshare.restaurant.Actifities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;

import com.creativeshare.restaurant.Fragments.Fragment_Cart;
import com.creativeshare.restaurant.Fragments.Fragment_Catogry;
import com.creativeshare.restaurant.Fragments.Fragment_Details;
import com.creativeshare.restaurant.Fragments.Fragment_Home;
import com.creativeshare.restaurant.Fragments.Fragment_Sub_Catogry;
import com.creativeshare.restaurant.Language.Language;
import com.creativeshare.restaurant.Model.Sub_Catogry_Model_Slide;
import com.creativeshare.restaurant.R;
import com.creativeshare.restaurant.Room_Database.MyDoe;
import com.creativeshare.restaurant.Room_Database.My_Database;

public class MainActivity extends AppCompatActivity {
    private Fragment_Home fragment_home;
    private Fragment_Catogry fragment_catogry;
    private Fragment_Cart fragment_cart;
    private Fragment_Sub_Catogry fragment_sub_catogry;
    private Fragment_Details fragment_details;
    int fragment_count = 0;
    FragmentManager fragmentManager;


    public static My_Database my_database;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase, Language.getLanguage(newBase)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            fragmentManager = this.getSupportFragmentManager();
            my_database= Room.databaseBuilder(getApplicationContext(), My_Database.class,"orderdb").allowMainThreadQueries().build();
            move_between_fragment();
            DisplayFragmentHome();
            DisplayFragmentCatogry();

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

    public void DisplayFragmentCatogry() {


        if (fragment_catogry == null) {
            fragment_catogry = Fragment_Catogry.newInstance();
        }
        if (fragment_cart != null && fragment_cart.isAdded()) {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).hide(fragment_cart).commit();
        }
        if (fragment_catogry.isAdded()) {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).show(fragment_catogry).commit();
        } else {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).add(R.id.fragment_main_child, fragment_catogry, "fragment_catogry").addToBackStack("fragment_catogry").commit();

        }

        if (fragment_home != null && fragment_home.isAdded()) {
            fragment_home.UpdateAHBottomNavigationPosition(0);
        }
    }

    public void DisplayFragmentCart() {

        if (fragment_cart == null) {
            fragment_cart = Fragment_Cart.newInstance();
        }
        if (fragment_catogry != null && fragment_catogry.isAdded()) {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).hide(fragment_catogry).commit();
        }
        if (fragment_cart.isAdded()) {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).show(fragment_cart).commit();
        } else {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).add(R.id.fragment_main_child, fragment_cart, "fragment_cart").addToBackStack("fragment_cart").commit();

        }

        if (fragment_home != null && fragment_home.isAdded()) {
            fragment_home.UpdateAHBottomNavigationPosition(1);
        }
    }

    public void DisplayFragmentSub_Catogry(String name, int id) {

        fragment_count += 1;

        fragment_sub_catogry = Fragment_Sub_Catogry.newInstance(name,id);


        if (fragment_sub_catogry.isAdded()) {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).show(fragment_sub_catogry).commit();
        } else {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).add(R.id.fragment_app_container, fragment_sub_catogry, "fragment_sub_catogry").addToBackStack("fragment_sub_catogry").commit();
        }


    }



    public void move_between_fragment() {
        overridePendingTransition(R.anim.enter, R.anim.exit);

    }

    @Override
    public void onBackPressed() {
        Back();
    }

    public void Back() {
        if (fragment_count > 1) {
            fragment_count -= 1;
            super.onBackPressed();
        } else {
            if (fragment_home != null && fragment_home.isVisible()) {
                if (fragment_catogry != null && fragment_catogry.isVisible()) {
                    finish();
                } else {
                    DisplayFragmentCatogry();
                }
            } else {
                DisplayFragmentHome();
            }
        }
    }


    public void updatecart() {
        if(fragment_cart!=null){
            fragment_cart.update_database();
        }
    }

    public void updatetotal() {
        if(fragment_cart!=null){
            fragment_cart.update_total();
        }
    }
    public void DisplayFragmnetDetials(Sub_Catogry_Model_Slide.Sub sub) {
        fragment_count += 1;

        fragment_details = Fragment_Details.newInstance(sub);


        if (fragment_details.isAdded()) {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).show(fragment_details).commit();
        } else {
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit).add(R.id.fragment_app_container, fragment_details, "fragment_details").addToBackStack("fragment_details").commit();
        }
    }

}
