package com.example.psychometrictest.TEST;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.psychometrictest.Login.MainActivity;
import com.example.psychometrictest.R;
import com.example.psychometrictest.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class UserAccountFragment extends Fragment {

    private Button logout;
    private TextView name,email,mobile;
    private DatabaseReference reference;


    public UserAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_account, container, false);

        logout = view.findViewById(R.id.logout_btn);
        name = view.findViewById(R.id.pro_name);
        email=view.findViewById(R.id.pro_email);
        mobile=view.findViewById(R.id.pro_number);
        reference = FirebaseDatabase.getInstance().getReference("User");

       reference.child("7356062660").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
           @Override
           public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {

               if(task.isSuccessful()) {
                   User user;
                   try {
                       user = Objects.requireNonNull(task.getResult()).getValue(User.class);
                       if (user != null) {
                           email.setText(user.getEmail());
                           name.setText(user.getFirstname());
                           mobile.setText(user.getPhonenumber());
                       }

                   } catch (Exception exception) {
                       exception.printStackTrace();
                   }

               }

           }
       });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(),MainActivity.class));

            }
        });
        return view;
    }
}