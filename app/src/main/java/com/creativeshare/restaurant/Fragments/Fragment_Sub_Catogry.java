package com.creativeshare.restaurant.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.creativeshare.restaurant.Actifities.MainActivity;
import com.creativeshare.restaurant.Adapter.Sub_Catogries_Adapter;
import com.creativeshare.restaurant.Model.Catogry_Model_Slide;
import com.creativeshare.restaurant.Model.Sub_Catogry_Model_Slide;
import com.creativeshare.restaurant.R;
import com.creativeshare.restaurant.Room_Database.My_Database;
import com.creativeshare.restaurant.Room_Database.Order;
import com.creativeshare.restaurant.preferences.Preferences;
import com.creativeshare.restaurant.remote.Api;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Sub_Catogry extends Fragment {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Button add_cart;
    private ImageView back;
    private TextView title;
    private Catogry_Model_Slide catogry_model_slide;
    private Preferences preferences;
    private int image[];
    private String name[], current_lang;
    private ArrayList<Sub_Catogry_Model_Slide.Sub> catogry_model_slides;
    private Sub_Catogries_Adapter catogries_adapter;
    private MainActivity activity;
    final static private String Tag = "name";
    final static private String Tag2 = "id";
    private ByteArrayOutputStream stream;
    private Bitmap bitmap;
    private Order order;
    private boolean check = false;

    public static Fragment_Sub_Catogry newInstance(String name, int id) {
        Fragment_Sub_Catogry fragment_sub_catogry = new Fragment_Sub_Catogry();
        Bundle bundle = new Bundle();
        bundle.putString(Tag, name);
        bundle.putInt(Tag2, id);
        fragment_sub_catogry.setArguments(bundle);
        return fragment_sub_catogry;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub__catogry, container, false);
        ititview(view);
        getsubcatogry(getArguments().getInt(Tag2));
        return view;
    }

    private void getsubcatogry(int id) {
        Api.getService().getsubcatogries(id).enqueue(new Callback<Sub_Catogry_Model_Slide>() {
            @Override
            public void onResponse(Call<Sub_Catogry_Model_Slide> call, Response<Sub_Catogry_Model_Slide> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    catogry_model_slides = response.body().getSub();
                    catogries_adapter = new Sub_Catogries_Adapter(catogry_model_slides, activity);
                    recyclerView.setLayoutManager(new GridLayoutManager(activity, 4));
                    recyclerView.setAdapter(catogries_adapter);
                } else {
                    Log.e("code", response.errorBody() + "" + response.code());
                }
            }

            @Override
            public void onFailure(Call<Sub_Catogry_Model_Slide> call, Throwable t) {
                Log.e("code", t.getMessage());
                progressBar.setVisibility(View.GONE);

            }
        });
    }

    private void ititview(View view) {
        activity = (MainActivity) getActivity();
        image = new int[]{R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4};
        recyclerView = view.findViewById(R.id.catogry);
        recyclerView.setItemViewCacheSize(25);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.tv_title);
        add_cart = view.findViewById(R.id.add_cart);
        progressBar = (ProgressBar) view.findViewById(R.id.progBar2);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(activity, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        title.setText(getArguments().getString(Tag));
        preferences = Preferences.getInstance();
        current_lang = preferences.getlang(activity);
        if (current_lang.equals("ar")) {
            back.setRotation(180);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.Back();
            }
        });
        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtocart();
                if (check) {
                    activity.Back();

                    activity.DisplayFragmentCart();
                }
            }
        });

    }

    private void addtocart() {
        check = false;
        for (int i = 0; i < catogry_model_slides.size(); i++) {
            View view = recyclerView.getChildAt(i);
            CheckBox checkBox = view.findViewById(R.id.checkbox);
            TextView name = view.findViewById(R.id.txt_name);
            TextView price = view.findViewById(R.id.txt_price);
            ImageView imageView = view.findViewById(R.id.img1);
            if (checkBox.isChecked()) {
                order = new Order();
                check = true;
                order.setName(name.getText().toString());
                order.setPrice(price.getText().toString());
                order.setPrices(Double.parseDouble(price.getText().toString().replaceAll("[^0-9\\.]", "")));
                order.setAmount(1);
                bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

// convert bitmap to byte
                stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte imageInByte[] = stream.toByteArray();
                order.setImage(imageInByte);
                activity.my_database.myDoe().add_order(order);
                activity.updatecart();
                activity.updatetotal();
            }
        }
    }


}
