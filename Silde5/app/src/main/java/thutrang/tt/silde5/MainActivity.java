package thutrang.tt.silde5;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import thutrang.tt.silde5.sqlite.DBHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper dbHelper =new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        database.close();
    }
}