package com.example.psychometrictest.TEST;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androchef.happytimer.countdowntimer.CircularCountDownView;
import com.androchef.happytimer.countdowntimer.HappyTimer;
import com.example.psychometrictest.R;
import com.fevziomurtekin.customprogress.Dialog;
import com.fevziomurtekin.customprogress.Type;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class Questions_Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView questions;
    private CircularProgressIndicator noOfquestions;
    private TextView progressText;
    private static final String TAG = "Questions_Activity";
    private MaterialButton option1, option2, option3, option4;
    private Button share, next;
    private ArrayList<QuestionModel> questionList;
    private int position = 0;
    private int answerCount = 0;
    private int progress = 1;
    private String qAnswer;
    private boolean isQuestionFull = false;
    private CircularCountDownView countDownView;
    private DatabaseReference reference;
    QuestionModel questionModel;
    private Dialog progressDialog;
    private ConstraintLayout parentLayout;
    private Animation animation;
    private boolean isCountDownFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_);

        //hooks
        questions = findViewById(R.id.questions);
        noOfquestions = findViewById(R.id.number_indicator);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        share = findViewById(R.id.share_btn);
        next = findViewById(R.id.next_btn);
        parentLayout = findViewById(R.id.question_parent);
        progressText = findViewById(R.id.progress_text);
        animation = AnimationUtils.loadAnimation(this, R.anim.blink_anim);
        countDownView = findViewById(R.id.circularCountDownView);


        reference = FirebaseDatabase.getInstance().getReference("Categories");
        progressDialog = findViewById(R.id.progress);
        progressDialog.settype(Type.INTERWIND);
        progressDialog.show();
        countDownView.initTimer(10);

        questionList = new ArrayList<>();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadQuestions();
            }
        });

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

        countDownView.setOnTickListener(new HappyTimer.OnTickListener() {
            @Override
            public void onTick(int i, int i1) {

            }

            @Override
            public void onTimeUp() {
                countDownTimerState();
                if(!isCountDownFinished)
                    loadQuestions();
                else {
                    countDownView.stopTimer();
                    countDownView.resetTimer();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fetchData();
    }


    private void loadQuestions() {

        if (!isQuestionFull) {
            option1.setBackgroundColor(getResources().getColor(R.color.white));
            option2.setBackgroundColor(getResources().getColor(R.color.white));
            option3.setBackgroundColor(getResources().getColor(R.color.white));
            option4.setBackgroundColor(getResources().getColor(R.color.white));
            countDownTimerState();
            if (position == questionList.size() - 1) {
                noOfquestions.setProgress(position + 1);
                progressText.setText(String.valueOf(position + 1));
                questions.setText(questionList.get(position).getQuestion());
                option1.setText(questionList.get(position).getOptionA());
                option2.setText(questionList.get(position).getOptionB());
                option3.setText(questionList.get(position).getOptionC());
                option4.setText(questionList.get(position).getOptionD());
                position = 0;
                isQuestionFull = true;

            } else {
                noOfquestions.setProgress(position + 1);
                progressText.setText(String.valueOf(position + 1));
                questions.setText(questionList.get(position).getQuestion());
                option1.setText(questionList.get(position).getOptionA());
                option2.setText(questionList.get(position).getOptionB());
                option3.setText(questionList.get(position).getOptionC());
                option4.setText(questionList.get(position).getOptionD());
                position++;

            }
        } else {
            isCountDownFinished = true;
            AlertDialog.Builder builder = new AlertDialog.Builder(Questions_Activity.this);
            builder.setCancelable(true);
            View view = getLayoutInflater().inflate(R.layout.alertdialog_layout, null);
            Button cancel = view.findViewById(R.id.cancel);
            Button sumbit = view.findViewById(R.id.submit_btn);

            builder.setView(view);
            AlertDialog alertDialog = builder.create();
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
            sumbit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    Intent intent = new Intent(Questions_Activity.this, Score_Activity.class);
                    intent.putExtra("rightAnswer", answerCount+"");
                    intent.putExtra("totalQuestion", questionList.size());
                    startActivity(intent);
                }
            });
            if(!alertDialog.isShowing())
                alertDialog.show();
            else
                alertDialog.dismiss();

//            Toast.makeText(this, "All Questions are loaded", Toast.LENGTH_SHORT).show();
        }
    }

    private void countDownTimerState() {
        countDownView.stopTimer();
        countDownView.resetTimer();
        countDownView.initTimer(10);
        countDownView.startTimer();
    }

    private void fetchData() {
        Log.d(TAG, "fetchData: called");
        reference.child("Communication").child("Test1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Log.d(TAG, "onDataChange: fetching data");
                    questionModel = new QuestionModel();
                    questionModel.setQuestion(dataSnapshot.child("Question").getValue(String.class));
                    questionModel.setOptionA(dataSnapshot.child("Option").child("A").getValue(String.class));
                    questionModel.setOptionB(dataSnapshot.child("Option").child("B").getValue(String.class));
                    questionModel.setOptionC(dataSnapshot.child("Option").child("C").getValue(String.class));
                    questionModel.setOptionD(dataSnapshot.child("Option").child("D").getValue(String.class));
                    questionModel.setAnswer(dataSnapshot.child("ANSWER").getValue(String.class));
                    questionList.add(questionModel);

                    if (snapshot.getChildrenCount() == questionList.size()) {
                        Log.d(TAG, "onDataChange: Entire list Loaded");
                        if (progressDialog.isShown())
                            progressDialog.gone();
                        noOfquestions.setMax(questionList.size());
                        loadQuestions();
                        parentLayout.setVisibility(View.VISIBLE);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    @Override
    public void onClick(View v) {

        qAnswer = questionList.get(Integer.parseInt(progressText.getText().toString())-1).getAnswer().toString();
//        Log.d(TAG, "onClick: "+ qAnswer);

        Log.d(TAG, "onClick: " + String.valueOf(qAnswer));
        option1.setBackgroundColor(Color.parseColor("#ffffff"));
        option2.setBackgroundColor(Color.parseColor("#ffffff"));
        option3.setBackgroundColor(Color.parseColor("#ffffff"));
        option4.setBackgroundColor(Color.parseColor("#ffffff"));

        switch (v.getId()) {
            case R.id.option1:
                v.setBackgroundColor(getResources().getColor(R.color.blue));
                if(option1.getText().toString().toLowerCase().equals(qAnswer.toLowerCase()))
                    answerCount++;
                break;

            case R.id.option2:
                v.setBackgroundColor(getResources().getColor(R.color.blue));
                if(option2.getText().toString().toLowerCase().equals(qAnswer.toLowerCase()))
                    answerCount++;
                break;

            case R.id.option3:
                v.setBackgroundColor(getResources().getColor(R.color.blue));
                if(option3.getText().toString().toLowerCase().equals(qAnswer.toLowerCase()))
                    answerCount++;
                break;

            case R.id.option4:
                v.setBackgroundColor(getResources().getColor(R.color.blue));
                if(option4.getText().toString().toLowerCase().equals(qAnswer.toLowerCase()))
                    answerCount++;
                break;

        }

    }

}