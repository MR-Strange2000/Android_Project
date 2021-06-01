package com.example.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homepage.Completed;
import com.example.homepage.MainActivity;
import com.example.homepage.R;
import com.example.homepage.all_task_disp;
import com.example.homepage.post_job_format;
import com.example.homepage.project_details;
import com.example.homepage.selectedjob;

import java.util.ArrayList;
import java.util.List;

import static com.example.homepage.all_task_disp.ClassPath;

public class open_adapter extends RecyclerView.Adapter<open_adapter.MyviewHolder> {

    all_task_disp context;
    String ClassPath;
   // private all_task_disp activity;
    static List<post_job_format> list2;
    static List<project_details> list3;
    private static RecyclerView.ViewHolder holder;


    public open_adapter(all_task_disp context, List<post_job_format> list, String ClassPath){
        this.context = context;
        this.ClassPath = ClassPath;
        //this.activity = activity;
        this.list2 = list;
    }
    public static post_job_format getJobs(int id) {
        return list2.get(id);
    }
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View c = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_list_items1 , parent , false);
        return new MyviewHolder(c);
    }

    @Override
    public void onBindViewHolder(@NonNull  open_adapter.MyviewHolder holder, int position) {
        final int id = position;
        holder.title.setText(list2.get(position).getName());
        holder.desc.setText(list2.get(position).getDescription());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // project_details theRemovedItem = list3.get(position);
                // remove your item from data base
                list2.remove(position);  // remove the item from list
                notifyItemRemoved(position);
                //context.startActivity(new Intent(context, MainActivity.class).putExtra("id", id));

            }
        });
        /*private void removeItem(int pos) {
            int actualPosition = holder.getAdapterPosition();
            list2.remove(actualPosition);
            notifyItemRemoved(actualPosition);
            notifyItemRangeChanged(actualPosition, list2.size());
        }*/
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClassPath.equals("JobSelected")) {

                    context.startActivity(new Intent(context, Completed.class).putExtra("id", id));
                }
               /* else {
                    Toast.makeText(joblistAdapter.this, "Server Error! Check your Internet Connection", Toast.LENGTH_SHORT).show();
                }*/
                //context.startActivity(new Intent(context, selectedPoojaPandit.class).putExtra("id", id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    public  static class MyviewHolder extends  RecyclerView.ViewHolder {
        public View relativeLayout;
        Button delete;
        TextView title , desc;
        public MyviewHolder(@NonNull  View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.jobName);
            desc = itemView.findViewById(R.id.detailsText);
            relativeLayout = itemView.findViewById(R.id.itemComponent);
            delete = itemView.findViewById(R.id.button200);
        }
    }

}
