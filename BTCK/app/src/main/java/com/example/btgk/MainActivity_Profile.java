package com.example.btgk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btgk.databinding.ActivityMainProfileBinding;

public class MainActivity_Profile extends DrawerBaseActivity {
    ActivityMainProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}