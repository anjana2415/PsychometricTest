package com.example.psychometrictest.TEST;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.psychometrictest.Login.RealtimeDB;
import com.example.psychometrictest.MycompleteListener;
import com.example.psychometrictest.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class test extends AppCompatActivity {
    private RecyclerView testview;
//    private Toolbar toolbar;

    private Dialog proogressDialog;
    private static final String TAG = "firebase_test";
    private ArrayList<TestModel> testList;
    private TestAdapter adapter;
//    private FirebaseFirestore reference = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        testview = findViewById(R.id.testrecycler);
        testList = new ArrayList<>();

        // FirebaseQuery.loadTestData();
//
        getSupportActionBar().setTitle(RealtimeDB.homeList.get(getIntent().getIntExtra("position",0)).getName());
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        testview.setLayoutManager(layoutManager);
        loadtestdata();
//        fetchData();
        adapter = new TestAdapter(testList);
        testview.setAdapter(adapter);

    }
    private void loadtestdata() {
        testList = new ArrayList<>();
        testList.add(new TestModel("1", 50, 20));
        testList.add(new TestModel("2", 0, 40));
        testList.add(new TestModel("3", 0, 60));
    }
}