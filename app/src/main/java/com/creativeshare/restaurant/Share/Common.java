package com.creativeshare.restaurant.Share;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.creativeshare.restaurant.Actifities.MainActivity;
import com.creativeshare.restaurant.Model.Sub_Catogry_Model_Slide;
import com.creativeshare.restaurant.R;
import com.creativeshare.restaurant.Room_Database.Order;
import com.creativeshare.restaurant.Tags.Tags;
import com.creativeshare.restaurant.preferences.Preferences;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;


public class Common {
    public static int re = 0;


    public static void CreateUserNotSignInAlertDialog(final Context context, final Sub_Catogry_Model_Slide.Sub model) {


        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setCancelable(true)
                .create();


        View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null);
        Button btn_ok = view.findViewById(R.id.yes_sure);
        Button btn_cancell = view.findViewById(R.id.No_cancell);
        TextView price = view.findViewById(R.id.tetxt_price);
        TextView tv_msg = view.findViewById(R.id.tv_msg);
        MainActivity activity = (MainActivity) context;

        final Preferences preferences = Preferences.getInstance();
        if (preferences.getlang(activity).equals("ar")) {
            tv_msg.setText(model.getAr_title());
        } else {
            tv_msg.setText(model.getEn_title());
        }
        price.setText(model.getPrice());
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

                if (context instanceof MainActivity) {
                    MainActivity activity = (MainActivity) context;
                    Order order = new Order();
                    if (preferences.getlang(activity).equals("ar")) {
                        order.setName(model.getAr_title());
                    } else {
                        order.setName(model.getEn_title());

                    }
                    order.setPrice(model.getPrice());
                    order.setPrices(Double.parseDouble(model.getPrice().toString().replaceAll("[^0-9\\.]", "")));
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
                }


            }
        });


        btn_cancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // dialog.getWindow().getAttributes().windowAnimations= R.style.dialog_congratulation_animation;
        dialog.setCanceledOnTouchOutside(false);
        dialog.setView(view);
        dialog.show();
    }

}
