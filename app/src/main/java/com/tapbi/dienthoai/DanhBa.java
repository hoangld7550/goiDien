package com.tapbi.dienthoai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.tapbi.dienthoai.Model.ContactModel;

import java.io.InputStream;
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





    }

    public List<ContactModel> getContacts(Context ctx) {
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
        return list;


    }
}
