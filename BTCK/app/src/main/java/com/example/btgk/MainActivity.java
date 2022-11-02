package com.example.btgk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.btgk.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends DrawerBaseActivity {

    ActivityMainBinding binding;
    ArrayList<String> fruitList = new ArrayList<>();
    ArrayList<String> fruitImages = new ArrayList<>();
    ArrayList<String> content = new ArrayList<>();
    ArrayList<String> money = new ArrayList<>();
    ArrayList<Integer> key = new ArrayList<>();
    ListView lv;
    DatabaseReference database;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://btgk-c12ce-default-rtdb.firebaseio.com/");

        setContentView(binding.getRoot());

        ArrayList<Food> foodArrayList = new ArrayList<>();

//        for(int i = 0 ; i< fruitList.size(); i++ ){
//            Food fd = new Food(fruitList.get(i),content.get(i), money.get(i), fruitImages.get(0),"","");
//            foodArrayList.add(fd);
//        }

        ListAdapter listAdapter = new ListAdapter(MainActivity.this,foodArrayList);
        if(!listAdapter.isEmpty()){
            listAdapter.clear();
        }

        database.child("Shop").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    key.add(Integer.parseInt(dataSnapshot.getKey()));
                    fruitList.add(dataSnapshot.child("title").getValue(String.class));
                    content.add(dataSnapshot.child("content").getValue(String.class));
                    money.add(dataSnapshot.child("money").getValue(String.class));
                    fruitImages.add(dataSnapshot.child("images").getValue(String.class));
                    Food fd = new Food(key.get(i),fruitList.get(i),content.get(i), money.get(i), fruitImages.get(i),"","");
                    foodArrayList.add(fd);
                    i+=1;
                }
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.lv.setAdapter(listAdapter);
        binding.lv.setClickable(true);

        binding.lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id)
            {
                Intent kk =new Intent(MainActivity.this, MainActivity_EDIT.class);
                kk.putExtra("title",fruitList.get(pos));
                kk.putExtra("content",content.get(pos));
                kk.putExtra("money",money.get(pos));
                kk.putExtra("images",fruitImages.get(pos));
                kk.putExtra("pos",key.get(pos));
                startActivity(kk);
                return false;
            }
        });

        binding.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent kk =new Intent(MainActivity.this, FoodInfo.class);
                kk.putExtra("title",fruitList.get(position));
                kk.putExtra("content",content.get(position));
                kk.putExtra("money",money.get(position));
                kk.putExtra("imageid",fruitImages.get(position));
                startActivity(kk);
            }
        });

    }
}