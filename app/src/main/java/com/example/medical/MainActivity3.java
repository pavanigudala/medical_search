package com.example.medical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity3 extends AppCompatActivity {

    EditText et1, et2;
    Button login, signup;
    private FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        et1 = findViewById(R.id.editText1);
        et2 = findViewById(R.id.editText2);
        login = findViewById(R.id.button1);
        signup = findViewById(R.id.button2);
        fbAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et1.getText().toString();
                String pwd = et2.getText().toString();
                fbAuth.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(MainActivity3.this, new OnCompleteListener<AuthResult>() {
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent it = new Intent(MainActivity3.this,MainActivity5.class);
                                    startActivity(it);
                                } else {
                                    Toast.makeText(MainActivity3.this, "login failed", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity3.this,MainActivity4.class);
                startActivity(it);
            }
        });
    }
}
