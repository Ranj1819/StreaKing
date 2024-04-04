package com.example.streaking.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.streaking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignupPage extends Fragment {
    EditText email, password;
    TextView loginButton;
    FirebaseAuth mAuth;
    Button signupButton;
//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            getParentFragmentManager().beginTransaction().replace(R.id.main_activity_layout, new HomePage()).commitNow();
//        }
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_signup_page, container, false);
        mAuth=FirebaseAuth.getInstance();
        email=view.findViewById(R.id.email);
        password=view.findViewById(R.id.password);
        loginButton=view.findViewById(R.id.loginButton);
        signupButton=view.findViewById(R.id.signUpButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                mAuth.createUserWithEmailAndPassword(tempEmail, tempPassword)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "AccountRegistered", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        loginButton.setOnClickListener(v->{
            getParentFragmentManager().beginTransaction().replace(R.id.main_activity_layout, new LoginPage()).commitNow();
        });
        return view;
    }
}