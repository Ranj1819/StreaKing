package com.example.streaking.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.streaking.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Start_Page extends Fragment {
    Button startButton;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_start_page, container, false);
        startButton=view.findViewById(R.id.start_button);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            getParentFragmentManager().beginTransaction().replace(R.id.main_activity_layout, new HomePage()).addToBackStack(null).commit();
        }
        startButton.setOnClickListener(v->{
            getParentFragmentManager().beginTransaction().replace(R.id.main_activity_layout, new LoginPage()).commitNow();
        });
        return view;
    }
}