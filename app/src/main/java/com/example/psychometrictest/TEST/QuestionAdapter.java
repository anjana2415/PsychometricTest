package com.example.psychometrictest.TEST;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psychometrictest.R;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<QuestionModel> questionList;
    public QuestionAdapter(List<QuestionModel> questionList) {
        this.questionList = questionList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_item_layout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button optionA,optionB,optionC,optionD;
        private TextView questions;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            questions = itemView.findViewById(R.id.question);
            optionA=itemView.findViewById(R.id.option1);
            optionB=itemView.findViewById(R.id.option2);
            optionC=itemView.findViewById(R.id.option3);
            optionD=itemView.findViewById(R.id.option4);
        }
        private void setData(final  int pos)
        {
            questions.setText(questionList.get(pos).getQuestion());
            optionA.setText(questionList.get(pos).getOptionA());
            optionB.setText(questionList.get(pos).getOptionB());
            optionC.setText(questionList.get(pos).getOptionC());
            optionD.setText(questionList.get(pos).getOptionD());

        }
    }
}
