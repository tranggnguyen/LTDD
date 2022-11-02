package com.example.btgk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity_Login extends AppCompatActivity {

    Button button;
    EditText edtUser, edtPass;
    TextView textViewRegister, textview;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        // lay du lieu tu text user
        edtUser = findViewById(R.id.edtUser);
        // lay du lieuj tu text pass chua
        edtPass = findViewById(R.id.edtPass);
        button = (Button) findViewById(R.id.button);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        textViewRegister = (TextView) findViewById(R.id.textViewRegister);

        fAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                //String user = "trang";
                //String Pass = "123456";
                String User = edtUser.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                if (TextUtils.isEmpty(User)) {
                    edtUser.setError("Gmail is Required");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    edtPass.setError("Password is Required");
                    return;
                }
                if (pass.length() < 6) {
                    edtPass.setError("Password must be > 6 Characters");
                    return;
                }
                //  register the user firebase
                fAuth.signInWithEmailAndPassword(User, pass).addOnCompleteListener((task) -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity_Login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        if (checkBox.isChecked()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("taikhoan", edtUser.getText().toString().trim());
                            editor.putString("matkhau", edtPass.getText().toString().trim());
                            editor.putBoolean("checked", true);
                            editor.commit();
                        } else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("taikhoan");
                            editor.remove("matkhau");
                            editor.remove("checked");
                            editor.commit();
                        }

                        // cai ni la nhay qua main r ne

                    } else {
                        Toast.makeText(MainActivity_Login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        textViewRegister.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity_Login.this, MainActivity_Register.class);
                startActivity(i);
            }
        });
    }
}
