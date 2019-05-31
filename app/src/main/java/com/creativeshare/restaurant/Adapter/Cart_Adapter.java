package com.creativeshare.restaurant.Adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.creativeshare.restaurant.Actifities.MainActivity;
import com.creativeshare.restaurant.R;
import com.creativeshare.restaurant.Room_Database.Order;
import com.creativeshare.restaurant.preferences.Preferences;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.Eyas_Holder> {
    List<Order> list;
    Context context;
    MainActivity activity;
    Preferences preferences;
    String current_lang;
    double price;

    public Cart_Adapter(List<Order> list, Context context) {
        this.list = list;
        this.context = context;
        activity = (MainActivity) context;
        preferences = Preferences.getInstance();
        current_lang = preferences.getlang(activity);


    }

    @Override
    public Eyas_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_cart, viewGroup, false);
        Eyas_Holder eas = new Eyas_Holder(v);
        return eas;
    }

    @Override
    public void onBindViewHolder(@NonNull final Eyas_Holder viewHolder, int i) {
        Order model = list.get(i);
        viewHolder.txt_name.setText(model.getName());
        viewHolder.txt_price.setText(model.getPrice() + "");
        Bitmap bp = BitmapFactory.decodeByteArray(model.getImage(), 0, model.getImage().length);
        viewHolder.im.setImageBitmap(bp);
        viewHolder.txt_amount.setText(model.getAmount() + "");
        viewHolder.txt_amount2.setText(model.getAmount() + "");
        viewHolder.txt_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = Double.parseDouble(viewHolder.txt_price.getText().toString().replaceAll("[^0-9\\.]", "")) / Integer.parseInt(viewHolder.txt_amount.getText().toString());
                viewHolder.txt_amount.setText((Integer.parseInt(viewHolder.txt_amount.getText().toString()) + 1) + "");
                viewHolder.txt_amount2.setText((Integer.parseInt(viewHolder.txt_amount2.getText().toString()) + 1) + "");
                viewHolder.txt_price.setText(price * Integer.parseInt(viewHolder.txt_amount.getText().toString()) + viewHolder.txt_price.getText().toString().replaceAll("[0-9\\.]", ""));
                activity.my_database.myDoe().update(list.get(viewHolder.getLayoutPosition()).getId(), Integer.parseInt(viewHolder.txt_amount.getText().toString()), viewHolder.txt_price.getText().toString(), price * Integer.parseInt(viewHolder.txt_amount.getText().toString()));
                activity.updatetotal();
            }
        });
        viewHolder.txt_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = Double.parseDouble(viewHolder.txt_price.getText().toString().replaceAll("[^0-9\\.]", "")) / Integer.parseInt(viewHolder.txt_amount.getText().toString());
                if (Integer.parseInt(viewHolder.txt_amount.getText().toString()) > 1) {
                    viewHolder.txt_amount.setText((Integer.parseInt(viewHolder.txt_amount.getText().toString()) - 1) + "");
                    viewHolder.txt_amount2.setText((Integer.parseInt(viewHolder.txt_amount2.getText().toString()) - 1) + "");
                    viewHolder.txt_price.setText(price * Integer.parseInt(viewHolder.txt_amount.getText().toString()) + viewHolder.txt_price.getText().toString().replaceAll("[0-9\\.]", ""));
                    activity.my_database.myDoe().update(list.get(viewHolder.getLayoutPosition()).getId(), Integer.parseInt(viewHolder.txt_amount.getText().toString()), viewHolder.txt_price.getText().toString(), price * Integer.parseInt(viewHolder.txt_amount.getText().toString()));
                    activity.updatetotal();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Eyas_Holder extends RecyclerView.ViewHolder {
        TextView txt_name, txt_price, txt_amount, txt_amount2;
        CircleImageView im;
        ImageView txt_increase, txt_decrease;

        public Eyas_Holder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.tv_name);
            txt_price = itemView.findViewById(R.id.tv_cost);
            txt_amount = itemView.findViewById(R.id.amount);
            txt_amount2 = itemView.findViewById(R.id.tv_amount);
            txt_increase = itemView.findViewById(R.id.image_increase);
            txt_decrease = itemView.findViewById(R.id.image_decrease);
            im = itemView.findViewById(R.id.image);
            if (current_lang.equals("ar")) {
                txt_name.setGravity(Gravity.END);
                txt_price.setGravity(Gravity.END);
            }
        }


    }
}
