package com.example.psychometrictest.TEST;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.psychometrictest.Login.RealtimeDB;
import com.example.psychometrictest.MycompleteListener;
import com.example.psychometrictest.R;

import static com.example.psychometrictest.TEST.FirebaseQuery.f_skilllist;


public class StartTest_Activity extends AppCompatActivity {

    private TextView cat_name,test_no;
    private Button start_testbtn;
    private ImageView back_start;
    public static int sTAPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test_);

        cat_name=findViewById(R.id.cat_name);
        test_no = findViewById(R.id.Test_no);
        start_testbtn=findViewById(R.id.start_test);
        back_start=findViewById(R.id.starttest_back);
        cat_name.setText(RealtimeDB.homeList.get(sTAPosition).getName());
//        loadQuestions(new MycompleteListener() {
//            @Override
//            public void onSuccess() {
//                setData();
//                Toast.makeText(StartTest_Activity.this, "success", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void OnFailure() {
//                Toast.makeText(StartTest_Activity.this, "somthing went wrong!", Toast.LENGTH_SHORT).show();
//            }
//        });
//        back_start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                StartTest_Activity.this.finish();
//
//            }
//        });

        start_testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Questions_Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
//    public void  setData()
//    {
//        cat_name.setText(f_skilllist.get(FirebaseQuery.f_selected_cat_index).getName());
//        test_no.setText("Test No. "+String.valueOf(FirebaseQuery.f_selected_test_index + 1));
//
//
//    }
}