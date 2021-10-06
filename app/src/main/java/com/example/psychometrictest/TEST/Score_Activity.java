package com.example.psychometrictest.TEST;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.psychometrictest.R;
import com.google.android.material.badge.BadgeUtils;

public class Score_Activity extends AppCompatActivity {
    private TextView score;
    private Button report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_);
        report= findViewById(R.id.score_btn);
        score=findViewById(R.id.score);
    }
}