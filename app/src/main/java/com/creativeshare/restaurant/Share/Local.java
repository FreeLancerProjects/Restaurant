package com.creativeshare.restaurant.Share;


import android.app.Application;
import android.content.Context;

import com.creativeshare.restaurant.Language.Language;


public class Local extends Application {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase, Language.getLanguage(newBase)));

    }

}

