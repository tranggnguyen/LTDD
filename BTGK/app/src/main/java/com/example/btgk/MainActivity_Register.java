package com.example.btgk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_Register extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
        TextView dn = (TextView) findViewById(R.id.dn);
        Button btn=(Button) findViewById(R.id.btn) ;
        btn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity_Register.this, MainActivity_Login.class);
                startActivity(i);
            }
        });

        dn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity_Register.this, MainActivity_Login.class);
                startActivity(i);
            }
        });
    }
}