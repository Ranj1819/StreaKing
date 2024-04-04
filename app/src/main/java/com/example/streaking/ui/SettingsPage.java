package com.example.streaking.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.streaking.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SettingsPage extends Fragment {
    FirebaseAuth auth;
    Button logoutButton;
    ImageView addPage, homePage;
    FirebaseUser user;
    TextView mail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_settings_page, container, false);

        auth=FirebaseAuth.getInstance();
        logoutButton=view.findViewById(R.id.logout);
        mail=view.findViewById(R.id.email);
        user=auth.getCurrentUser();
        addPage=view.findViewById(R.id.addPage);
        homePage=view.findViewById(R.id.homePage);

        if(user==null){
            getParentFragmentManager().beginTransaction().replace(R.id.main_activity_layout, new LoginPage()).commitNow();
        }
        else{
            mail.setText(user.getEmail());
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                getParentFragmentManager().beginTransaction().replace(R.id.main_activity_layout, new LoginPage()).commitNow();
            }
        });
        addPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.main_activity_layout, new AddTaskPage()).commitNow();

            }
        });

        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.main_activity_layout, new HomePage()).commitNow();
            }
        });

        return view;
    }
}