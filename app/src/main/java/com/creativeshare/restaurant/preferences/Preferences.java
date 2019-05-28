package com.creativeshare.restaurant.preferences;

import android.content.Context;
import android.content.SharedPreferences;


import com.creativeshare.restaurant.Tags.Tags;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Preferences {

    private static Preferences instance = null;

    private Preferences() {
    }

    public static Preferences getInstance() {
        if (instance == null) {
            instance = new Preferences();
        }
        return instance;
    }

    public void create_update_lang(Context context, String session) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("langu", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lang", session);
        editor.apply();


    }

    public String getlang(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("langu", Context.MODE_PRIVATE);
        String session = preferences.getString("lang", Tags.pref_lang);
        return session;
    }
}