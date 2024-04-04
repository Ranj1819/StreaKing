package com.example.streaking.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.streaking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends Fragment {
    EditText email, password;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    TextView signupButton;
    ProgressBar progressBar;
    Button loginButton;


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login_page, container, false);
        Log.d("Error Ranjith", "onCreateView: ");
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            getParentFragmentManager().beginTransaction().replace(R.id.main_activity_layout, new HomePage()).addToBackStack(null).commit();
        }
        email=view.findViewById(R.id.email);
        password=view.findViewById(R.id.password);
        loginButton=view.findViewById(R.id.logInButton);
        signupButton=view.findViewById(R.id.signUpButton);
        progressBar=view.findViewById(R.id.progressBar);

        signupButton.setOnClickListener(v->{
            getParentFragmentManager().beginTransaction().replace(R.id.main_activity_layout, new SignupPage()).commitNow();
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String tempEmail, tempPassword;
                tempEmail=String.valueOf(email.getText());
                tempPassword=String.valueOf(password.getText().toString());

                if(TextUtils.isEmpty(tempEmail)){
                    Toast.makeText(getContext(), "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(tempPassword)){
                    Toast.makeText(getContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(tempEmail, tempPassword)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Login Successful.", Toast.LENGTH_SHORT).show();
                                    getParentFragmentManager().beginTransaction().replace(R.id.main_activity_layout, new HomePage()).commitNow();

                                } else {
                                    Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        return view;
    }
}