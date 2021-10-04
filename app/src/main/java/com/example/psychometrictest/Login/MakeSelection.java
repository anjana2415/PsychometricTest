package com.example.psychometrictest.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.psychometrictest.R;

public class MakeSelection extends AppCompatActivity {
  private Button email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_selection);
        email=findViewById(R.id.emai_btn);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SetNewPassword.class);
                startActivity(i);
            }
        });
    }
}