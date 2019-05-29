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

import de.hdodenhof.circleimageview.CircleImageView;


public class SplashScreenActivity extends AppCompatActivity {

   private Preferences preferences;
private CircleImageView circleImageView;
   private FrameLayout im;
    Animation animation1 ;
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
circleImageView=findViewById(R.id.progress_circular);
circleImageView.setImageResource(R.drawable.logo);
         Animation animation;

animation= AnimationUtils.loadAnimation(getBaseContext(), R.anim.lanuch);
        im.startAnimation(animation);
     animation1=
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_anomation);
animation.setAnimationListener(new Animation.AnimationListener() {
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        circleImageView.startAnimation(animation1);


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
});
animation1.setAnimationListener(new Animation.AnimationListener() {
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
Intent intent=new Intent(SplashScreenActivity.this,MainActivity.class);
startActivity(intent);
finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
});
    }
}
