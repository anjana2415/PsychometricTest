package com.example.psychometrictest.TEST;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.psychometrictest.R;

import java.util.List;

public class skillAdapter extends BaseAdapter {
    public static List<skilltest> skilltestList;
    Context context;

    public skillAdapter(List<skilltest> skilltestList, Context context) {
        this.skilltestList = skilltestList;
        this.context = context;
    }



    @Override
    public int getCount() {
        return skilltestList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View viewadapter;
        if (convertView == null){
            viewadapter = LayoutInflater.from(parent.getContext()).inflate(R.layout.skillitemlayout,parent,false);


        }else {
            viewadapter = convertView;

        }

        viewadapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FirebaseQuery.f_selected_cat_index = position;
                Intent intent = new Intent(context,test.class);
                intent.putExtra("position", position);
                StartTest_Activity.sTAPosition = position;

             //   intent.putExtra("CAT_INDEX",position);
                context.startActivity(intent);

            }
        });

        TextView cat_name = viewadapter.findViewById(R.id.cat_name);
        TextView no_qn = viewadapter.findViewById(R.id.n_qn);

        cat_name.setText(skilltestList.get(position).getName());
        no_qn.setText( "No of Test" + "\n" + String.valueOf(skilltestList.get(position).getNoOfTests()));

    return viewadapter;
    }
}
