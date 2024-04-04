package com.example.streaking.fortaskrecycler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streaking.R;
import com.example.streaking.ui.MainActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {
    ArrayList<TaskModel> taskModelArrayList;
    public TaskAdapter(ArrayList<TaskModel> taskModelArrayList) {
        this.taskModelArrayList = taskModelArrayList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.taskrecycler, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        TaskModel taskModel=taskModelArrayList.get(position);
        holder.taskName.setText(taskModel.getTaskName());
        holder.streakCount.setText(String.valueOf(taskModel.getStreakCount()));
        String lastLogin=taskModel.getLastLogin();
        long millis=System.currentTimeMillis();
        String currDate=(new Date(millis)).toString();
//        int lastDate=Integer.parseInt(lastLogin.substring(lastLogin.length()-2));
//        int curr=Integer.parseInt(currDate.substring(currDate.length()-2));
        LocalDate date1 = LocalDate.parse(lastLogin, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate date2 = LocalDate.parse(currDate, DateTimeFormatter.ISO_LOCAL_DATE);
        if(!date1.equals(date2)){
            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Tasks").child(taskModel.getTaskName());
            if(date1.plusDays(1).equals(date2)){
                if(taskModel.isStreakIcon()){
                    databaseReference.child("streakIcon").setValue(false);
                }else{
                    databaseReference.child("streakCount").setValue(0);
                }
            }else{
                databaseReference.child("streakCount").setValue(0);
                databaseReference.child("streakIcon").setValue(false);
            }
            databaseReference.child("lastLogin").setValue(currDate);
        }
        if(taskModel.isStreakIcon()){
            holder.streakIcon.setImageResource(R.drawable.on_fire);
        }else{
            holder.streakIcon.setImageResource(R.drawable.off_fire);
        }

        holder.remove.setOnClickListener(v->{
            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Tasks").child(taskModel.getTaskName());
            databaseReference.removeValue();
        });

        holder.streakIcon.setOnClickListener(v->{
            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Tasks").child(taskModel.getTaskName());
            int setCount=taskModel.getStreakCount();
            if(!taskModel.isStreakIcon()){
                databaseReference.child("streakIcon").setValue(true);
                setCount+=1;
                databaseReference.child("streakCount").setValue(setCount);
            }else{
                databaseReference.child("streakIcon").setValue(false);
                setCount-=1;
                databaseReference.child("streakCount").setValue(setCount);
            }
        });


    }


    @Override
    public int getItemCount() {
        return taskModelArrayList.size();
    }



}
