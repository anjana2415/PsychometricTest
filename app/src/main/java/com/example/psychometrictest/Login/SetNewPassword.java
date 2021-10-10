package com.example.psychometrictest.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psychometrictest.R;
import com.example.psychometrictest.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SetNewPassword extends AppCompatActivity {
    private TextView userEmail;
    private EditText userPassword, confPassword;
    private Button changePassword;
    private DatabaseReference reference;
    private static final String TAG = "SetNewPassword";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
        //hooks
        userEmail = findViewById(R.id.user_email_text);
        userPassword = findViewById(R.id.pass_forget);
        confPassword = findViewById(R.id.confirm_password_forget);
        changePassword = findViewById(R.id.change_pass_btn);

        //db_initialization
        reference = FirebaseDatabase.getInstance().getReference("User");

        userEmail.setText(getIntent().getStringExtra("userEmail"));

        initializeUser();

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetPassword();
            }
        });

    }

    private void initializeUser() {

        String phone = getIntent().getStringExtra("phoneNumber");

        reference.child(phone).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    user = new User();
                    user.setEmail(snapshot.child("email").getValue(String.class));
                    user.setFirstname(snapshot.child("firstname").getValue(String.class));
                    user.setLastname(snapshot.child("lastname").getValue(String.class));
                    user.setUsername(snapshot.child("username").getValue(String.class));
                    user.setPhonenumber(snapshot.child("phonenumber").getValue(String.class));
                    user.setPassword(snapshot.child("password").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void resetPassword() {

        String pwd = userPassword.getText().toString().trim();
        String conf_pwd = confPassword.getText().toString().trim();
        if (pwd.equals(conf_pwd)) {
            user.setPassword(pwd);

            reference.child(getIntent().getStringExtra("phoneNumber")).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SetNewPassword.this, "Password Updated successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),ForgetPasswordSuccess.class);
                        startActivity(i);
                    }
                }
            });


        }
    }


}

