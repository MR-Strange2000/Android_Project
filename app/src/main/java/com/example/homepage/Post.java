package com.example.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.Spinner;
import android.widget.TextView;
=======
>>>>>>> ee9c69062394dfc7c1172afc51c32ecf78d2326d
import android.widget.Toast;

import com.example.utils.post_job_format;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
<<<<<<< HEAD
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import static com.example.homepage.R.layout.add;
import static com.example.homepage.R.layout.search;

public class Post extends AppCompatActivity{
        Spinner dropdown;
        TextView selected;
    String[] items = new String[]{"website" , "it" , "android" , "one" , "two"};
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;


    EditText name , description , price;
    Button post;


=======
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Post extends AppCompatActivity{
//    Spinner dropdown;
//    String[] items = new String[]{"website" , "it" , "android" , "one" , "two"};
    EditText name , description , price;
    Button post;
//    private FirebaseDatabase database;
//    private DatabaseReference myRef;
    private FirebaseFirestore firestoreDB;
>>>>>>> ee9c69062394dfc7c1172afc51c32ecf78d2326d
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
       dropdown = (Spinner)findViewById(R.id.spinner);
       ArrayAdapter<String> adapter = new ArrayAdapter<>(this , android.R.layout.simple_spinner_item , items);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       dropdown.setAdapter(adapter);
       dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               selected.setText(parent.getSelectedItem().toString());
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
       selected = (TextView)findViewById(R.id.txtsel);

        name = (EditText)findViewById(R.id.project_name);
        description = (EditText)findViewById(R.id.project_description);
        price = (EditText)findViewById(R.id.price);
<<<<<<< HEAD
        post = (Button)findViewById(R.id.btn_reg);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("project/" + mAuth.getInstance().getCurrentUser().getUid());

=======
        post = (Button)findViewById(R.id.post_project);

//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("project");
        firestoreDB = FirebaseFirestore.getInstance();
>>>>>>> ee9c69062394dfc7c1172afc51c32ecf78d2326d
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post_job_format job = new post_job_format();
                job.setName(name.getText().toString());
                job.setPrice(price.getText().toString());
                job.setBid("0");
                job.setPayment("f");
                job.setDescription(description.getText().toString());
                job.setCatagory(dropdown.getSelectedItem().toString());


//                String key = myRef.push().getKey();
//                job.setRid(key);

                firestoreDB.collection("project").add(job).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Job successfully added", Toast.LENGTH_SHORT).show();
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Job adding failed try again later", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

}