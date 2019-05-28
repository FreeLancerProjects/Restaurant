package com.creativeshare.restaurant.Actifities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.creativeshare.restaurant.Language.Language;
import com.creativeshare.restaurant.R;
import com.creativeshare.restaurant.Tags.Tags;
import com.creativeshare.restaurant.preferences.Preferences;


public class SplashScreenActivity extends AppCompatActivity {

    Preferences preferences;

    FrameLayout im;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase, Language.getLanguage(newBase)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreenmaker);
        preferences=Preferences.getInstance();
im=(FrameLayout)findViewById(R.id.fl);
         Animation animation;

animation= AnimationUtils.loadAnimation(getBaseContext(), R.anim.lanuch);
        im.startAnimation(animation);
animation.setAnimationListener(new Animation.AnimationListener() {
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
            Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
            i.putExtra("param","4");

            startActivity(i);

        finish();

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
});
    }
}
