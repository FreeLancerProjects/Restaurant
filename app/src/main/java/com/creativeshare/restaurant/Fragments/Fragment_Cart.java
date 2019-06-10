package com.creativeshare.restaurant.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creativeshare.restaurant.Actifities.MainActivity;
import com.creativeshare.restaurant.Adapter.Cart_Adapter;
import com.creativeshare.restaurant.Adapter.Sub_Catogries_Adapter;
import com.creativeshare.restaurant.Model.Catogry_Model_Slide;
import com.creativeshare.restaurant.R;
import com.creativeshare.restaurant.Room_Database.Order;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Cart extends Fragment {
    private RecyclerView recyclerView;

    private int image[];
    private String name[];
    private List<Order> orders;
    private Cart_Adapter cart_adapter;
    private MainActivity activity;
    private TextView total;
private Button delet;
private LinearLayout linearLayout;
    public static Fragment_Cart newInstance() {
        return new Fragment_Cart();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        ititview(view);
        return view;
    }

    private void ititview(View view) {
        activity = (MainActivity) getActivity();
        orders = activity.my_database.myDoe().getorder();
        recyclerView = view.findViewById(R.id.catogry);
        recyclerView.setItemViewCacheSize(25);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        total = view.findViewById(R.id.total_price);
        delet=view.findViewById(R.id.empty);
        linearLayout=view.findViewById(R.id.linear_empty);
        total.setText(activity.my_database.myDoe().total_price()+"");
        cart_adapter = new Cart_Adapter(orders, activity);
        if(orders==null||orders.isEmpty()){
            linearLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            delet.setVisibility(View.GONE);
        }
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 1));
        recyclerView.setAdapter(cart_adapter);
        delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.my_database.myDoe().deleteallorder();
                update_database();
                update_total();
            }
        });
    }

  /*  private void ititview(View view) {
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
        cart_adapter =new Cart_Adapter(catogry_model_slides,activity);
        recyclerView.setLayoutManager(new GridLayoutManager(activity,1));
        recyclerView.setAdapter(cart_adapter);



    }
*/
  public void update_database(){
      orders.removeAll(orders);
      orders.addAll(activity.my_database.myDoe().getorder());
      cart_adapter.notifyDataSetChanged();
      if(orders.isEmpty()){
          linearLayout.setVisibility(View.VISIBLE);
          recyclerView.setVisibility(View.GONE);
          delet.setVisibility(View.GONE);
      }
      else{

          linearLayout.setVisibility(View.GONE);
          recyclerView.setVisibility(View.VISIBLE);
          delet.setVisibility(View.VISIBLE);
      }
  }
    public void update_total(){
       total.setText(activity.my_database.myDoe().total_price()+"");
    }
}
