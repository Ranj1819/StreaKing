package com.example.streaking.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.sql.Date;
import com.example.streaking.R;
import com.example.streaking.fortaskrecycler.TaskModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class AddTaskPage extends Fragment {

    ImageView home, setting;
    Button addTaskButton;
    EditText taskEditText;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase database=FirebaseDatabase.getInstance();

    AlertDialog.Builder builder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_add_task_page, container, false);

        home=view.findViewById(R.id.HomePage);
        setting=view.findViewById(R.id.settingPage);
        addTaskButton=view.findViewById(R.id.addTaskButton);
        builder=new AlertDialog.Builder(getContext());
        taskEditText=view.findViewById(R.id.task);
        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.main_activity_layout, new HomePage()).commitNow();
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.main_activity_layout, new SettingsPage()).commitNow();
            }
        });
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName=String.valueOf(taskEditText.getText());
                if(TextUtils.isEmpty(taskName)) {
                    Toast.makeText(getContext(), "Please enter the task!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseReference myRef = database.getReference("Tasks").child(taskName);
                    builder.setTitle("Add").setMessage("Do you want to add this task").setCancelable(true).setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            long todayDate=System.currentTimeMillis();
                            Date date=new Date(todayDate);
                            TaskModel tempModel=new TaskModel(taskName, 0, false, date.toString());
                            myRef.setValue(tempModel);
                            Toast.makeText(getContext(), "Added successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getContext(), "Cancelled successfully", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    }).show();
                }
            }
        });
        return view;
    }
}