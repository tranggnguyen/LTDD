package thutrang.tt.contentprovider.demo3n;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.PagerAdapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.TextView;

import java.net.URI;

import thutrang.tt.contentprovider.R;


public class Demo31nMainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    private static final int CHO_PHEP_DOC=1;
    private Uri uriContact;
    private String contactID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo31n_main);
        phanQuyen();
        textView = findViewById(R.id.textview);
        button =findViewById(R.id.button);
        button.setOnClickListener(v->{
            startActivityForResult(new Intent(
                    Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI),CHO_PHEP_DOC);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CHO_PHEP_DOC && resultCode ==-1)
        {
            uriContact = data.getData();
            String contactName=getContactName();
            String contactNumber=getcontactNumber();
            textView.setText(contactName +"-"+contactNumber);
        }

    }

    @SuppressLint("Range")
    public String getContactName()
    {
        String contactName=null;
        Cursor cursor= getContentResolver().query(uriContact,null,null,null,null);
        if(cursor.moveToFirst())//di chuyển ve ban ghi dau tien
        {
            contactName = cursor.getString(cursor.getColumnIndex(
                    ContactsContract.Contacts.DISPLAY_NAME));
        }
        cursor.close();
        return contactName;
    }
    @SuppressLint("Range")
    public String getcontactNumber(){
        String contactNumber = null;
        Cursor cursorID=getContentResolver().query(uriContact,new  String[]{ContactsContract.Contacts._ID},null,null,null);
        if(cursorID.moveToFirst())
        {
            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
        }
        cursorID.close();
        //Lay cac thong tin ve ban ghi

        Cursor cursorPhone = getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        new  String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID +"=?AND"+
                        ContactsContract.CommonDataKinds.Phone.TYPE+"="+
                         ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,
                        new String[]{contactID},null);
        if(cursorPhone.moveToFirst())
        {
            //LAY SO DIEN THOAI
            contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER
            ));
        }
        cursorPhone.close();
        return contactNumber;
    }

    public boolean phanQuyen() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE) ==
                    PackageManager.PERMISSION_GRANTED
                    && checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_NUMBERS) ==
                    PackageManager.PERMISSION_GRANTED
                    && checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_NUMBERS) ==
                    PackageManager.PERMISSION_GRANTED
                    && checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_NUMBERS) ==
                    PackageManager.PERMISSION_GRANTED) {
                return true;//nếu quyền đã khai báo trong android manifest->return true
            } else {
                //nếu quyền chưa được khai báo trong android manifest- thì xin quyền
                ActivityCompat.requestPermissions(Demo31nMainActivity.this,
                        new String[]{Manifest.permission.READ_PHONE_NUMBERS,
                                Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.READ_CONTACTS,
                                Manifest.permission.WRITE_CONTACTS}, 1);
                return false;
            }
        }
        else{
            return true;
        }
    }
}

