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
import com.example.homepage.begin_project;
import com.example.homepage.post_job_format;
import com.example.homepage.project_details;
import com.example.homepage.selectedjob;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.getIntent;
import static com.example.homepage.all_task_disp.ClassPath;

public class open_adapter extends RecyclerView.Adapter<open_adapter.MyviewHolder> {

    all_task_disp context;
    String ClassPath="Job Selected";


   // private all_task_disp activity;
   public static post_job_format jobselected;
    static List<post_job_format> list2;
    //private static project_details list3;
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
   //public static project_details getJobss(int id1) {
       // return list3;
   // }
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View c = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_list_items , parent , false);
        return new MyviewHolder(c);
    }

    @Override
    public void onBindViewHolder(@NonNull  open_adapter.MyviewHolder holder, int position) {
        final int id = position;
        holder.title.setText(list2.get(position).getName());
        holder.desc.setText(list2.get(position).getDescription());


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClassPath.equals("JobSelected")) {

                    context.startActivity(new Intent(context, begin_project.class).putExtra("id", id));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    public  static class MyviewHolder extends  RecyclerView.ViewHolder {
       private int id;
        public View relativeLayout;

        TextView title , desc;
        public MyviewHolder(@NonNull  View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.jobName);
            desc = itemView.findViewById(R.id.detailsText);
            relativeLayout = itemView.findViewById(R.id.itemComponent);

            //firebase = FirebaseDatabase.getInstance();
            //user = FirebaseAuth.getInstance().getCurrentUser();
            //databaseReference = firebase.getReference("project_details");
            //databaseReference = firebase.getReference("project");
            //db = firebase.getReference("project_details");
           //final int id = getIntent().getIntExtra("id", 0);
            //jobselected =  open_adapter.getJobs(id);
        }



    }
    /*public static String getSelectedJobId() {
        return jobselected.getRid();
    }*/
}



