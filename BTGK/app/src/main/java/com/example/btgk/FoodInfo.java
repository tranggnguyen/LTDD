package com.example.btgk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.btgk.databinding.ActivityFoodInfoBinding;

public class FoodInfo extends DrawerBaseActivity {
    ActivityFoodInfoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFoodInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = this.getIntent();
        if (intent !=null){
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");
            String money = intent.getStringExtra("money");
            int imageid = intent.getIntExtra("imageid",R.drawable.apple);

            binding.title.setText(title);
            binding.content.setText(content);
            binding.money.setText(money);
            binding.img.setImageResource(imageid);
        }
    }
}