package com.example.btgk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

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
            String imageid = intent.getStringExtra("imageid");

            byte[] decodedString = Base64.decode(imageid, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0,decodedString.length);


            binding.title.setText(title);
            binding.content.setText(content);
            binding.money.setText(money);
            binding.img.setImageBitmap(decodedByte);
        }
    }
}