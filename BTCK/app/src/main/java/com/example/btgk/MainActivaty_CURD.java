package com.example.btgk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btgk.databinding.ActivityMainActivatyCurdBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivaty_CURD extends DrawerBaseActivity {
    EditText title,money,content,images;
    FirebaseAuth fAuth;
    DatabaseReference database;
    ActivityMainActivatyCurdBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainActivatyCurdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        title = binding.title;
        money =binding.money;
        content =binding.content;
        images = binding.images;

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://btgk-c12ce-default-rtdb.firebaseio.com/");

        Button addBTN =(Button) binding.addBTN;
        fAuth = FirebaseAuth.getInstance();



        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText = title.getText().toString();
                String moneyText = money.getText().toString();
                String contentText = content.getText().toString();
                String imagesText = images.getText().toString();

                if(titleText.isEmpty() || moneyText.isEmpty() || contentText.isEmpty() || imagesText.isEmpty() ){
                    Toast.makeText(MainActivaty_CURD.this, "LÀM ƠN NHẬP ALL DỮ LIỆU", Toast.LENGTH_SHORT).show();
                }
                else{
                    database.child("Shop").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int i = 1;
                            boolean checkTitle = false;
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                if (dataSnapshot.child("title").getValue(String.class).equals(titleText)){
                                    Toast.makeText(MainActivaty_CURD.this, "ĐÃ CÓ SẢN PHẨM " + titleText, Toast.LENGTH_SHORT).show();
                                    checkTitle = true;
                                }
                                i = Integer.parseInt(dataSnapshot.getKey());
                            }
                            if(!checkTitle){
                                i+=1;
                                database.child("Shop").child(""+i).child("title").setValue(titleText);
                                database.child("Shop").child(""+i).child("content").setValue(contentText);
                                database.child("Shop").child(""+i).child("money").setValue(moneyText);
                                database.child("Shop").child(""+i).child("images").setValue(imagesText);

                                Toast.makeText(MainActivaty_CURD.this, "THÊM SẢN PHẨM THÀNH CÔNG ", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }

        });

    }
}