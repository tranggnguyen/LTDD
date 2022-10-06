package com.example.btgk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Food> {
    Context cM;
    public ListAdapter( Context context, ArrayList<Food> foodArrayList) {
        super(context, R.layout.layoutitem,foodArrayList);
        cM = context;
    }


    @NonNull
    @Override
    public android.view.View getView(int position, @Nullable android.view.View convertView, @NonNull ViewGroup parent) {
        Food fd = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layoutitem, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.image1);
        TextView title = convertView.findViewById(R.id.title);
        TextView content = convertView.findViewById(R.id.content);
        TextView money = convertView.findViewById(R.id.money);


        TextView add = convertView.findViewById(R.id.add);
        TextView remove = convertView.findViewById(R.id.remove);

        imageView.setImageResource(fd.imageId);
        title.setText(fd.title);
        content.setText(fd.content);
        money.setText(fd.money);
        add.setText(fd.add);
        add.setText(fd.remove);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(cM,
                        "Bạn đã thêm "+ title.getText() +" vào giỏ hàng "  ,Toast.LENGTH_SHORT).show();
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(cM,
                        "Bạn đã xóa "+ title.getText() +" trong giỏ hàng "  ,Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
