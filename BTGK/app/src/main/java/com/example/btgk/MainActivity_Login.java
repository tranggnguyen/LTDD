package com.example.btgk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_Login extends AppCompatActivity {

    Button button;
    TextView textViewRegister, textViewUser, textViewPass;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        sharedPreferences =getSharedPreferences("dataLogin",MODE_PRIVATE);
        // lay du lieu tu text user
        textViewUser = (TextView) findViewById(R.id.edtUser);
        // lay du lieuj tu text pass chua
        textViewPass =(TextView) findViewById(R.id.edtPass);
        button = (Button) findViewById(R.id.button);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        button.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                String user = "trang";
                String pass = "123456";
                //dang nhap tu lam them cai password di de hieu k
                if (textViewUser.getText().toString().equals(user) && textViewPass.getText().toString().equals(pass)){
                    Intent i = new Intent(MainActivity_Login.this, MainActivity.class);
                    Toast.makeText(MainActivity_Login.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                    if(checkBox.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan",textViewUser.getText().toString().trim());
                        editor.putString("matkhau",textViewPass.getText().toString().trim());
                        editor.putBoolean("checked",true);
                        editor.commit();
                    }
                    else{
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.remove("checked");
                        editor.commit();
                    }

                    startActivity(i);
                }
                else{
                    // hien loi
                    Toast.makeText(MainActivity_Login.this, "Dang nhap loi", Toast.LENGTH_SHORT).show();
                }
            }
        });


        textViewRegister = (TextView) findViewById(R.id.textViewRegister) ;
        textViewRegister.setOnClickListener(new android.view.View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity_Login.this, MainActivity_Register.class);
                startActivity(i);
            }
        });
    }
}