package com.example.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homepage.Browse;
import com.example.homepage.Completed;
import com.example.homepage.R;
import com.example.homepage.begin_project;
import com.example.homepage.in_progress_disp;
import com.example.homepage.post_job_format;
import com.example.homepage.selectedjob;

import java.util.List;

public class in_progressAdapter extends RecyclerView.Adapter<in_progressAdapter.MyViewHolder>{
    in_progress_disp context;
    String classPath2;
    static List<post_job_format> list3;

    public in_progressAdapter(in_progress_disp context, List<post_job_format> jobs, String classPath2) {
        this.context = context;
        this.list3 = jobs;
        this.classPath2 = classPath2;
    }

    public static post_job_format getJobs(int id) {
        return list3.get(id);
    }




    @NonNull
    @Override
    public in_progressAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_list_items, parent, false);
        return new in_progressAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull in_progressAdapter.MyViewHolder holder, int position) {
        final int id = position;
        holder.title.setText(list3.get(position).getName());
        holder.desc.setText(list3.get(position).getDescription());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(classPath2.equals("BeginProject")) {

                    context.startActivity(new Intent(context, Completed.class).putExtra("id", id));
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return list3.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title , desc;
        public RelativeLayout relativeLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.jobName);
            desc = itemView.findViewById(R.id.detailsText);
            relativeLayout = itemView.findViewById(R.id.itemComponent);
        }
    }
}

