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
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tapbi.dienthoai.Adapter.AdapterGY;
import com.tapbi.dienthoai.Model.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements iClick {
    String sdt = "";
    TextView tvSdt, tvSo1, tvSo2, tvSo3, tvSo4, tvSo5, tvSo6, tvSo7, tvSo8, tvSo9, tvSao, tvSo0, tvThang;
    ImageButton imgGoiDien, imgDanhBa;
    RecyclerView rcvGoiY;
    private TelephonyManager mTelephonyManager;
    List<ContactModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvSdt = findViewById(R.id.tvSdt);
        tvSo1 = findViewById(R.id.tvSo1);
        tvSo2 = findViewById(R.id.tvSo2);
        tvSo3 = findViewById(R.id.tvSo3);
        tvSo4 = findViewById(R.id.tvSo4);
        tvSo5 = findViewById(R.id.tvSo5);
        tvSo6 = findViewById(R.id.tvSo6);
        tvSo7 = findViewById(R.id.tvSo7);
        tvSo8 = findViewById(R.id.tvSo8);
        tvSo9 = findViewById(R.id.tvSo9);
        tvSao = findViewById(R.id.tvSao);
        tvSo0 = findViewById(R.id.tvSo0);
        tvThang = findViewById(R.id.tvThang);
        imgGoiDien = findViewById(R.id.imgGoiDien);
        imgDanhBa = findViewById(R.id.imgDanhBa);
        rcvGoiY = findViewById(R.id.rcvGoiYdb);

        Intent intent= getIntent();
        sdt= intent.getStringExtra("sdt");
        if(sdt==null){
            sdt="";
        }
        tvSdt.setText(sdt);
        imgDanhBa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DanhBa.class);
                startActivity(intent);
            }
        });

        tvSo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdt += "1";
                tvSdt.setText(sdt);
            }
        });

        tvSo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdt += "2";
                tvSdt.setText(sdt);
            }
        });
        tvSo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdt += "3";
                tvSdt.setText(sdt);
            }
        });
        tvSo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdt += "4";
                tvSdt.setText(sdt);
            }
        });
        tvSo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdt += "5";
                tvSdt.setText(sdt);
            }
        });
        tvSo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdt += "6";
                tvSdt.setText(sdt);
            }
        });
        tvSo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdt += "7";
                tvSdt.setText(sdt);
            }
        });
        tvSo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdt += "8";
                tvSdt.setText(sdt);
            }
        });
        tvSo9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdt += "9";
                tvSdt.setText(sdt);
            }
        });
        tvSao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdt += "*";
                tvSdt.setText(sdt);
            }
        });
        tvSo0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdt += "0";
                tvSdt.setText(sdt);
            }
        });
        tvThang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdt += "#";
                tvSdt.setText(sdt);
            }
        });

        imgGoiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goiDien();
            }
        });

        mTelephonyManager = (TelephonyManager) getSystemService(getApplicationContext().TELEPHONY_SERVICE);

        getContacts(this);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(MainActivity.this);
        rcvGoiY.setLayoutManager(linearLayoutManager);
        tvSdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("sdt thay doi: ", sdt);
                List<ContactModel> listGy= new ArrayList<>();
                if(sdt!=""){
                    for(int z=0; z<list.size(); z++){
                        if(list.get(z).mobileNumber.indexOf(sdt)>-1){
                            listGy.add(list.get(z));
                        }
                    }
                    AdapterGY adapter= new AdapterGY(MainActivity.this, listGy, MainActivity.this);
                    rcvGoiY.setAdapter(adapter);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }


    PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);

            switch (state) {
//                case TelephonyManager.CALL_STATE_IDLE:
//                    Toast.makeText(MainActivity.this, "CALL_STATE_IDLE", Toast.LENGTH_SHORT).show();
//                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    Toast.makeText(MainActivity.this, "Có chuông điện thoại", Toast.LENGTH_SHORT).show();
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Toast.makeText(MainActivity.this, "đang nhận cuộc gọi", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    protected void onResume() {
        super.onResume();
        mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }
    @Override
    protected void onStop() {
        super.onStop();
        mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_NONE);
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

            case 123:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    goiDien();
                } else {
                    Log.d("TAG", "Call Permission Not Granted");
                }
                break;



            default:
                break;
        }
    }

//lay danh ba
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


    public void goiDien() {
        if (!TextUtils.isEmpty(sdt)) {
            String dial = "tel:" + sdt;


            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        123);
            }


            else {
                Log.e("goi", sdt);
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }


        } else {
            Toast.makeText(MainActivity.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
        }
    }

    public void xoaSo(View view) {
        if (sdt.length() > 0)
            sdt = sdt.substring(0, sdt.length() - 1);
        tvSdt.setText(sdt);
    }


    @Override
    public void nhan(String sdtGy) {
        sdt=sdtGy;
        tvSdt.setText(sdt);
    }
}
