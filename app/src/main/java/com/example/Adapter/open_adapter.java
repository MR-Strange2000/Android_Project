package com.example.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homepage.R;
import com.example.homepage.all_task_disp;
import com.example.homepage.post_job_format;

import java.util.ArrayList;
import java.util.List;

public class open_adapter extends RecyclerView.Adapter<open_adapter.MyviewHolder> {

    private all_task_disp activity;
    public List<post_job_format> list = new ArrayList<>();


    public open_adapter(all_task_disp activity , List<post_job_format> list){
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
    public void onBindViewHolder(@NonNull  open_adapter.MyviewHolder holder, int position) {
        holder.title.setText(list.get(position).getName());
        holder.desc.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class MyviewHolder extends  RecyclerView.ViewHolder {
        TextView title , desc;
        public MyviewHolder(@NonNull  View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.jobName);
            desc = itemView.findViewById(R.id.detailsText);
        }
    }
}
