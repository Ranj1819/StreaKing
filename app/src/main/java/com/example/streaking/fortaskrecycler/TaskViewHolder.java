package com.example.streaking.fortaskrecycler;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streaking.R;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    TextView taskName, streakCount;
    Button remove;
    ImageView streakIcon;
    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);

        taskName=itemView.findViewById(R.id.taskName);
        remove=itemView.findViewById(R.id.removeButton);
        streakCount=itemView.findViewById(R.id.streakCount);
        streakIcon=itemView.findViewById(R.id.streakIcon);
    }
}
