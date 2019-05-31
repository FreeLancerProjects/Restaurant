package com.creativeshare.restaurant.Room_Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Orders")
public class Order {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "food_name")
    private String name;
    @ColumnInfo(name = "food_price")
    private String price;
    @ColumnInfo(name = "food_prices")
    private double prices;
    @ColumnInfo(name = "food_amount")
    private int amount;
    @ColumnInfo(name = "image")
private byte[] image;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
