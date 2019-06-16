package com.creativeshare.restaurant.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.creativeshare.restaurant.Actifities.MainActivity;
import com.creativeshare.restaurant.Model.Sub_Catogry_Model_Slide;
import com.creativeshare.restaurant.R;
import com.creativeshare.restaurant.Room_Database.Order;
import com.creativeshare.restaurant.Tags.Tags;
import com.creativeshare.restaurant.preferences.Preferences;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;


public class Fragment_Details extends Fragment {
    private MainActivity activity;
    private Button btn_ok, btn_cancell;
    private TextView price, tv_msg, des;
    final static private String Tag = "model";
    private Preferences preferences;
    private Sub_Catogry_Model_Slide.Sub model;
    private ImageView back,image_detials;

    public static Fragment_Details newInstance(Sub_Catogry_Model_Slide.Sub model) {
        Fragment_Details fragment = new Fragment_Details();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Tag, model);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        intitview(view);
        return view;
    }

    private void intitview(View view) {
        model = (Sub_Catogry_Model_Slide.Sub) getArguments().getSerializable(Tag);
        btn_ok = view.findViewById(R.id.yes_sure);
        btn_cancell = view.findViewById(R.id.No_cancell);

        price = view.findViewById(R.id.tetxt_price);

        tv_msg = view.findViewById(R.id.tv_msg);
image_detials=view.findViewById(R.id.img1);
        des = view.findViewById(R.id.des);
        back=view.findViewById(R.id.back);
        activity = (MainActivity) getActivity();

        preferences = Preferences.getInstance();
        Picasso.with(activity).load(Tags.IMAGE_URL_subcatogry+model.getImage()).fit().into(image_detials);
        if (preferences.getlang(activity).equals("ar")) {
            back.setRotation(180);
            tv_msg.setText(model.getAr_title());
            if (model.getAr_des() != null) {
                des.setText(model.getAr_des());
            }
        } else {
            tv_msg.setText(model.getEn_title());
            if (model.getEn_des() != null) {
                des.setText(model.getEn_des());
            }
        }
        price.setText(model.getPrice());
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order = new Order();
                if (preferences.getlang(activity).equals("ar")) {
                    order.setName(model.getAr_title());
                } else {
                    order.setName(model.getEn_title());

                }
                order.setPrice(model.getPrice());
                order.setPrices(Double.parseDouble(model.getPrice().replaceAll("[^0-9\\.]", "")));
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapFactory.decodeStream(new URL((Tags.IMAGE_URL_subcatogry + model.getImage())).openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ByteArrayOutputStream stream;
                stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte imageInByte[] = stream.toByteArray();
                order.setImage(imageInByte);
                order.setAmount(1);
                activity.my_database.myDoe().add_order(order);
                activity.updatecart();
                activity.updatetotal();
                activity.Back();
            }
        });
        btn_cancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.Back();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.Back();
            }
        });
    }


}
