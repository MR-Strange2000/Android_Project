package com.example.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homepage.R;
import com.example.homepage.all_task_disp;
import com.example.homepage.past_task_disp;
import com.example.homepage.post_job_format;
import com.example.homepage.selectedjob;

import java.util.ArrayList;
import java.util.List;



public class past_adapter extends RecyclerView.Adapter<past_adapter.MyviewHolder> {


    past_task_disp context;
    String ClassPath1;
    private past_task_disp activity;
    public List<post_job_format> list = new ArrayList<>();


    public past_adapter(past_task_disp activity, List<post_job_format> list, String classPath){
        this.context = context;
        this.ClassPath1 = ClassPath1;
        this.activity = activity;
        this.list = list;
    }
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View c = LayoutInflater.from(activity).inflate(R.layout.job_list_items , parent , false);
        return new MyviewHolder(c);
    }

    @Override
    public void onBindViewHolder(@NonNull  past_adapter.MyviewHolder holder, int position) {
        final int id = position;
        holder.title.setText(list.get(position).getName());
        holder.desc.setText(list.get(position).getDescription());
        /*holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClassPath.equals("JobSelected")) {

                    //context.startActivity(new Intent(context, selectedjob.class).putExtra("id", id));
                }
                else {
                    Toast.makeText(joblistAdapter.this, "Server Error! Check your Internet Connection", Toast.LENGTH_SHORT).show();
                }
                //context.startActivity(new Intent(context, selectedPoojaPandit.class).putExtra("id", id));
            }
        });*/
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class MyviewHolder extends  RecyclerView.ViewHolder {
        public View relativeLayout;
        TextView title , desc;
        public MyviewHolder(@NonNull  View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.jobName);
            desc = itemView.findViewById(R.id.detailsText);
            relativeLayout = itemView.findViewById(R.id.itemComponent);
        }
    }
}

