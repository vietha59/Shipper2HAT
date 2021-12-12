package com.example.shipper2hat;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class QuenMatKhau extends AppCompatActivity implements View.OnClickListener {


    EditText EmailKhoiPhuc;
    Button btnKhoiPhuc, btnQuayLai;

    FirebaseAuth firebaseAuth;
    DatabaseReference mRef;
    DatabaseShipper databaseShipper;
    FirebaseUser firebaseUser;
    String mail;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);

        /*-------------------- Gán giá trị --------------------*/
        EmailKhoiPhuc = findViewById(R.id.EmailKhoiPhuc);
        btnKhoiPhuc = findViewById(R.id.btnKhoiPhuc);
        btnQuayLai = findViewById(R.id.btnQuayLai);
        /*-------------------- END gán giá trị --------------------*/

        firebaseAuth = FirebaseAuth.getInstance();


        btnKhoiPhuc.setOnClickListener(this);
        btnQuayLai.setOnClickListener(this);
    }

    private boolean CheckEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnKhoiPhuc:
                String email = EmailKhoiPhuc.getText().toString().trim();
                boolean checkEmail = CheckEmail(email);
//                databaseShipper = new DatabaseShipper(getApplicationContext());
//                databaseShipper.getAll(new ShipperCallBack(){
//                    @Override
//                    public void onSuccess(ArrayList<Shipper> lists) {
//                        for (int i = 0; i < lists.size(); i++) {
//                            if (lists.get(i).getToken()!=null && lists.get(i).getToken().equalsIgnoreCase(firebaseUser.getUid())) {
//                                mail = lists.get(i).getEmail();
//                                EmailKhoiPhuc.setText(mail);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(String message) {
//
//                    }
//                });
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(QuenMatKhau.this, "Bạn hãy kiểm tra hộp thư Email", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(QuenMatKhau.this, DangNhapActivity.class));
                            //finish();
                        }
                    }
                });
                if (checkEmail) {

                }else {
                    Toast.makeText(QuenMatKhau.this,"Bạn hãy kiểm tra hộp thư Email",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnQuayLai:
                startActivity(new Intent(QuenMatKhau.this, DangNhapActivity.class));
                finish();
                break;
        }

    }
}
