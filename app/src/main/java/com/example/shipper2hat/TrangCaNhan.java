package com.example.shipper2hat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TrangCaNhan extends AppCompatActivity {
    TextView name, email, address,phone,birthday;
    Button capnhatthongtin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_ca_nhan);


        capnhatthongtin = findViewById(R.id.btn_cap_nhat);
        name = findViewById(R.id.tv_name);
        capnhatthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CapNhapThongTin.class);
                v.getContext().startActivity(intent);
            }
        });

    }
}