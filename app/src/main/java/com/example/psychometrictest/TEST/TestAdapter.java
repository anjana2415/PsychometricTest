package com.example.psychometrictest.TEST;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.psychometrictest.R;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private List<TestModel> testlist;

    public TestAdapter(List<TestModel> testlist) {
        this.testlist = testlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.testlayout,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int progress = testlist.get(position).getTopscore();
        holder.setData(position, progress);
    }

    @Override
    public int getItemCount() {
        return testlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView testno;
        private TextView topscore;
//        private ProgressBar testprogress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            testno = itemView.findViewById(R.id.testno);
            topscore = itemView.findViewById(R.id.scoretest);
//            testprogress = itemView.findViewById(R.id.testprogress);
        }

        private void setData(int pos, int progress) {
            testno.setText("Test No " + String.valueOf(pos + 1));
            topscore.setText(String.valueOf(progress) + "%");
//            testprogress.setProgress(progress);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                  FirebaseQuery.f_selected_test_index = pos;
                    Intent i = new Intent(itemView.getContext(), StartTest_Activity.class);
                    i.putExtra("position", pos);
                    itemView.getContext().startActivity(i);
                }
            });

        }
    }
}
