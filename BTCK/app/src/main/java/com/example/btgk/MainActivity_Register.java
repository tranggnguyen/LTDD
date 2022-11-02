package com.example.btgk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity_Register extends AppCompatActivity {
    EditText edtTen,edtGmail,edtSDT,edtMatkhau;
    Button btnDK;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
        edtTen = findViewById(R.id.user);
        edtGmail =findViewById(R.id.gmail);
        edtSDT = findViewById(R.id.sdt);
        edtMatkhau = findViewById(R.id.pass);
        TextView dn = (TextView) findViewById(R.id.dn);
        Button btnDK=(Button) findViewById(R.id.btn) ;
        fAuth = FirebaseAuth.getInstance();
//        if(fAuth.getCurrentUser() != null)
//        {
////            Toast.makeText(MainActivity_Register.this, "Error ! " +fAuth.getCurrentUser() , Toast.LENGTH_SHORT).show();
//
//            startActivity(new Intent(getApplicationContext(),MainActivity_Login.class));
//            finish();
//        }

        btnDK.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Gmail =edtGmail.getText().toString().trim();
                String pass =edtMatkhau.getText().toString().trim();
                if (TextUtils.isEmpty(Gmail)){
                    edtGmail.setError("Gmail is Required");

                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    edtMatkhau.setError("Password is Required");

                    return;
                }
                if(pass.length() <6 ){
                    edtMatkhau.setError("Password must be > 6 Characters");

                    return;
                }
                //register the user in firebase
                fAuth.createUserWithEmailAndPassword(Gmail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity_Register.this, "User created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity_Login.class));
                            // cai ni la nhay qua main r ne
                        }
                        else {
                            Toast.makeText(MainActivity_Register.this, "Error ! " +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        dn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity_Login.class));
            }
        });
    }
}
