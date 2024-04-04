package com.example.streaking.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.streaking.R;
import com.example.streaking.fortaskrecycler.TaskAdapter;
import com.example.streaking.fortaskrecycler.TaskModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class HomePage extends Fragment {
    ImageView add, setting;
    RecyclerView recyclerView;

    ArrayList<TaskModel> taskModels;
    TaskAdapter taskAdapter;
    DatabaseReference base;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home_page, container, false);
        add=view.findViewById(R.id.addTask);
        setting=view.findViewById(R.id.setting);
        recyclerView=view.findViewById(R.id.taskRecycler);
        taskModels=new ArrayList<>();
        base =FirebaseDatabase.getInstance().getReference();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.main_activity_layout, new AddTaskPage()).commitNow();
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.main_activity_layout, new SettingsPage()).commitNow();
            }
        });


        setupRecycler();




        return view;
    }

    public void setupRecycler(){

        base.child("Tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    taskModels.clear();
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                       TaskModel taskModel=dataSnapshot.getValue(TaskModel.class);
                       taskModels.add(taskModel);
                    }
                    taskAdapter=new TaskAdapter(taskModels);
                    recyclerView.setAdapter(taskAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}