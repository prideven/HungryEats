package com.prideven.android.hungryeats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.prideven.android.hungryeats.databinding.LoginBinding;


public class LoginActivity extends AppCompatActivity {
    EditText loginEmail, loginPassword;
    Button loginButton, registerButton, newPassButton;
    private static final int RC_SIGN_IN = 9001;
    private SignInButton signInButton;
    FirebaseAuth firebaseAuth;
    private LoginBinding lbinding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        lbinding = DataBindingUtil.setContentView(this, R.layout.login);
        loginButton = lbinding.loginButton;
        registerButton = lbinding.registerButton;
        firebaseAuth = FirebaseAuth.getInstance();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = lbinding.email.getText().toString();
                String password = lbinding.password.getText().toString();
                //signIn();
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "E-mail or password is wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(this,MainActivity.class));
        }

    }

    //    auth.signInWithEmailAndPassword (loginEmail, loginPassword).addOnCompleteListener(this,
    //    OnCompleteListener { task ->
//        if(task.isSuccessful) {
//            Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }else {
//            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
//        }
//    })


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }

}