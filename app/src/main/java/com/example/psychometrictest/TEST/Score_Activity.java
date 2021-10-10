package com.example.psychometrictest.TEST;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psychometrictest.R;
import com.google.android.material.badge.BadgeUtils;

public class Score_Activity extends AppCompatActivity {
    private TextView score;
    private LinearLayout report;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_);
        report= findViewById(R.id.report_view);
        score=findViewById(R.id.score);

        score.setText(getIntent().getStringExtra("rightAnswer"));


        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senEmail();
                Toast.makeText(Score_Activity.this, "Report Generated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void senEmail() {

        String[] TO = {"anjanarajeevan2017@gmail.com"};
//        String[] CC = {"xyz@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//        emailIntent.setData(Uri.parse("mailto:"));
//        emailIntent.setType("text/plain");
        emailIntent.setType("message/rfc822");
//        emailIntent.setDataAndType(Uri.parse("mailto:"), "text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Psychometric test Score");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Your test score in communication test : "+ getIntent().getStringExtra("rightAnswer"));

        startActivity(
                Intent
                        .createChooser(emailIntent,
                                "Choose an Email client :"));
//        if (emailIntent.resolveActivity(getPackageManager()) != null) {
//            Toast.makeText(this, "not null", Toast.LENGTH_SHORT).show();
//            startActivity(emailIntent);
//        }
    }
}