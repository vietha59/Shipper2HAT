package com.example.shipper2hat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Fragment;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements LocationListener {
    CircleImageView anhdaidien;
    RecyclerView  rcvmonan;
    ArrayList<Categories> datacategories;
    CategoryAdapter categoryAdapter;
    DatabaseCategories databaseCategories;
    Fragment maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhdaidien = findViewById(R.id.anhdaidien);
        anhdaidien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TrangCaNhan.class);
                v.getContext().startActivity(intent);
            }
        });

//        maps = findViewById(R.id.maps);
//        rcvmonan = findViewById(R.id.rcvmonan);
//
//        databaseCategories = new DatabaseCategories(getApplicationContext());
//        datacategories = new ArrayList<>();
//        CategoryAdapter categoryAdapter = new CategoryAdapter(datacategories, getApplicationContext());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
//        maps.setLayoutManager(linearLayoutManager);
//        maps.setHasFixedSize(true);
//        maps.setAdapter(categoryAdapter);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }


}