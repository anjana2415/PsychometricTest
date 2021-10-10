package com.example.psychometrictest.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.psychometrictest.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ForgetPassword extends AppCompatActivity {

    private EditText phone_num;
    private Button next;
    private static final String TAG = "ForgetPassword";
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        phone_num = findViewById(R.id.phone_num);
        next = findViewById(R.id.forget_next);
        reference = FirebaseDatabase.getInstance().getReference("User");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkUserExist();
            }
        });
    }

    private void checkUserExist() {
        String pno = phone_num.getText().toString().trim();

        reference.child(pno).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {

                    String email = snapshot.child("email").getValue(String.class);
                    Intent intent = new Intent(getApplicationContext(), SetNewPassword.class);
                    intent.putExtra("phoneNumber", pno);
                    intent.putExtra("userEmail", email);
                    startActivity(intent);
                } else {
                    Toast.makeText(ForgetPassword.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}