package com.creativeshare.restaurant.Room_Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDoe {
    @Insert
    void add_order(Order order);

    @Query("select * from orders")
     List<Order> getorder();

    @Query("SELECT SUM(food_prices) from orders")
     double total_price();
    @Query("UPDATE orders SET food_amount = :food_amount,food_price= :food_price,food_prices= :food_prices WHERE id = :tid")
    void update(int tid, int food_amount, String food_price, double food_prices);

    @Query("delete from orders")
    void deleteallorder();

}
