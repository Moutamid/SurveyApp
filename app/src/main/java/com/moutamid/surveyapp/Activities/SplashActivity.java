package com.moutamid.surveyapp.Activities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.fxn.stash.Stash;
import com.moutamid.surveyapp.HomeActivity;
import com.moutamid.surveyapp.R;

public class SplashActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    ImageView imageViewLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        imageViewLogo = findViewById(R.id.imageView);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageViewLogo, "y", -190);
        objectAnimator.setDuration(1500);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator);
        objectAnimator.start();
        int splashInterval = 1700;
        new Handler().postDelayed(this::goToApp, splashInterval);
    }

    public void goToApp() {
        if (!Stash.getString("name").toString().isEmpty()) {
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            finish();
        } else {
            startActivity(new Intent(SplashActivity.this, ProfileActivity.class));
            finish();
        }
    }


}