package com.example.psychometrictest.Login;

import androidx.annotation.NonNull;

import com.example.psychometrictest.TEST.skilltest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RealtimeDB {

    public static ArrayList<skilltest> homeList = new ArrayList<>();
    public static DatabaseReference reference;

    public static void getHomeList() {

        if (reference != null) {
            reference.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    homeList.clear();
                    for (DataSnapshot test : snapshot.getChildren()) {
                        skilltest skilltest = new skilltest(test.getKey().toString(),
                                test.child("No_of_test").getValue(Integer.class));
                        homeList.add(skilltest);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}
