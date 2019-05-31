package com.creativeshare.restaurant.Room_Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Order.class}, version = 1)
public abstract class My_Database extends RoomDatabase {
    public abstract MyDoe myDoe();
}
