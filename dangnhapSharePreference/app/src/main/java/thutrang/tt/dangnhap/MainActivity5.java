package thutrang.tt.dangnhap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity5 extends AppCompatActivity {
    ImageButton img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        img = (ImageButton) findViewById(R.id.btnBack);
        img.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity5.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}