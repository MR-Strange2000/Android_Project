package com.example.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homepage.Browse;
import com.example.homepage.R;
import com.example.homepage.post_job_format;
import com.google.firebase.database.core.Context;


import java.text.BreakIterator;
import java.util.List;

public class joblistAdapter extends RecyclerView.Adapter<joblistAdapter.MyViewHolder> {
    Context context;
    String classPath;
    static List<post_job_format> jobs;

    public joblistAdapter(Context context, List<post_job_format> jobs, String classPath) {
        this.context = context;
        this.jobs = jobs;
        this.classPath = classPath;
    }

    public static post_job_format getJobs(int id) {
        return jobs.get(id);
    }

    /*@NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.job_list_items, viewGroup, false);
        return new MyViewHolder(view);
    }*/


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_list_items, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        final int id = i;
        viewHolder.jobName.setText(jobs.get(i).getName());
        viewHolder.detailsText.setText(jobs.get(i).getCatagory() + " . " + jobs.get(i).getPrice() + " . " + jobs.get(i).getBid() + " bids");

        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(classPath.equals("selectedjob"))
                    context.startActivity(new Intent(context, selectedPooja.class).putExtra("id", id));
                else
                    context.startActivity(new Intent(context, selectedPoojaPandit.class).putExtra("id", id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView jobName, detailsText;
        public RelativeLayout relativeLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            jobName = itemView.findViewById(R.id.jobName);
            detailsText = itemView.findViewById(R.id.detailsText);
            relativeLayout = itemView.findViewById(R.id.itemComponent);
        }
    }
}
