package com.example.psychometrictest.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.psychometrictest.R;
import com.example.psychometrictest.TEST.FirebaseQuery;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashscreenActivity extends AppCompatActivity {
    ImageView imbg;
    LottieAnimationView splash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        imbg = findViewById(R.id.appbackground);
        splash=findViewById(R.id.splash);
        imbg.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        splash.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        // Access a Cloud Firestore instance from your Activity
//         FirebaseQuery.firestore = FirebaseFirestore.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashscreenActivity.this, MainActivity.class));
                finish();
            }
        },1000);

    }
}