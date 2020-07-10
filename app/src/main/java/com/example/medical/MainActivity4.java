 package com.example.medical;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

 public class MainActivity4 extends AppCompatActivity {
     private static final String TAG = "MainActivity4";
     private FirebaseAuth fAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText ShopName, mail, add, no, pwd;
    Button register;
    String adminID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ShopName = findViewById(R.id.editText1);
        mail = findViewById(R.id.editText2);
        add = findViewById(R.id.editText3);
        no = findViewById(R.id.editText4);
        pwd = findViewById(R.id.editText5);
        register = findViewById(R.id.button1);
        fAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String m = mail.getText().toString();
                String p = pwd.getText().toString();
                final String sn = ShopName.getText().toString();
                final String ad = add.getText().toString();
                final String pn = no.getText().toString();
                fAuth.createUserWithEmailAndPassword(m, p)
                        .addOnCompleteListener(MainActivity4.this, new OnCompleteListener<AuthResult>() {
                            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(MainActivity4.this, "registered successfully.Login to continue", Toast.LENGTH_SHORT).show();
                                        adminID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
                                        DocumentReference dr= db.collection("admins").document(adminID);
                                        Map<String, Object> admin = new HashMap<>();
                                        admin.put("shopName",sn);
                                        admin.put("email",m);
                                        admin.put("address",ad);
                                        admin.put("PhoneNumber",pn);
                                        dr.set(admin).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                            }
                                        });
                                        Intent it=new Intent(MainActivity4.this,MainActivity3.class);
                                        startActivity(it);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity4.this, "registration failed", Toast.LENGTH_SHORT).show();
                                    Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());

                                }
                            }
                        });
            }
        });
    }
}