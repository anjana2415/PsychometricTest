package com.example.psychometrictest.TEST;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.psychometrictest.Login.RealtimeDB;
import com.example.psychometrictest.R;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class SkillTestsFragment extends Fragment {


    public SkillTestsFragment() {

    }
    private GridView testview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_skill_tests, container, false);
        testview = view.findViewById(R.id .skilltest);
        skillAdapter adapter = new skillAdapter(RealtimeDB.homeList, getContext());
        testview.setAdapter(adapter);
        return view;
    }
   // private void loadcategory()
    //{
      //  skilllist.clear();
      //  skilllist.add(new skilltest("1","Agreeableness",2));
       // skilllist.add(new skilltest("2","Conscientiousness",20));
        //skilllist.add(new skilltest("3","Extraversion",20));
        //skilllist.add(new skilltest("4","Neuroticism",20));
        //skilllist.add(new skilltest("5","Openess",20));


   // }
}