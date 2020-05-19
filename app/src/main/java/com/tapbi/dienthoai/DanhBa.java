package com.tapbi.dienthoai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.tapbi.dienthoai.Adapter.Adapter;
import com.tapbi.dienthoai.Model.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class DanhBa extends AppCompatActivity {
    List<ContactModel> list = new ArrayList<>();
    RecyclerView rcvDanhBa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);

        rcvDanhBa= findViewById(R.id.rcvDanhBa);
        getContacts(this);


        Adapter adapter= new Adapter(DanhBa.this, list);
        rcvDanhBa.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(DanhBa.this);
        rcvDanhBa.setLayoutManager(linearLayoutManager);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 234:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getContacts(this);
                } else {
                    Log.d("TAG", "Contact Permission Not Granted");
                }
                break;

            default:
                break;
        }
    }


          public List<ContactModel> getContacts(Context ctx) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        234);
            }


            else {

            ContentResolver contentResolver = ctx.getContentResolver();
            Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        Cursor cursorInfo = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);

                        while (cursorInfo.moveToNext()) {
                            ContactModel info = new ContactModel();
                            info.id = id;
                            info.name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            info.mobileNumber = cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            list.add(info);
                        }
                        cursorInfo.close();
                    }
                }
                cursor.close();
            }

        }

        return list;
    }
}
