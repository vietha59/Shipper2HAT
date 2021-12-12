package com.example.shipper2hat;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;

public class DangNhapActivity extends AppCompatActivity implements  View.OnClickListener,  FirebaseAuth.AuthStateListener {

    Button mloginBtn;
    TextView btnquenmatkhau;
    EditText mEmail,mPassword;
    ProgressBar progressBar;
    DatabaseShipper databaseShipper;

    private FirebaseAuth firebaseAuth;
    private DatabaseShipper DatabaseShipper;
    ArrayList<Shipper> datastore;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*-------------------- Gán giá trị --------------------*/
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mloginBtn = findViewById(R.id.loginBtn);
        btnquenmatkhau = findViewById(R.id.btnquenmatkhau);
        progressBar = findViewById(R.id.progressBar);
        /*-------------------- Kết thúc gán giá trị --------------------*/


        firebaseAuth = FirebaseAuth.getInstance();
        mloginBtn.setOnClickListener(this);
        btnquenmatkhau.setOnClickListener(this);
    }

    /*-------------------- Đăng nhập bằng email, password --------------------*/

    private void login() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            mEmail.setError("Bắt buộc");
            mPassword.setError("Bắt buộc");
            Toast.makeText(getApplicationContext(), "Vui Lòng Nhập Đầy Đủ 2 Trường", Toast.LENGTH_SHORT).show();
            return;
        } else if (password.length() < 6) {
            mPassword.setError("Mật khẩu phải lớn hơn 6 ký tự");
            return;
        } else if (!email.matches("^[a-zA-Z][a-z0-9_\\.]{4,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$")) {
            mEmail.setError("Email không hợp lệ");
            return;
        } else {
            databaseShipper = new DatabaseShipper(getApplicationContext());
            datastore = new ArrayList<>();
            databaseShipper.getAll(new ShipperCallBack() {
                @Override
                public void onSuccess(ArrayList<Shipper> lists) {
                    datastore.clear();
                    datastore.addAll(lists);
                }

                @Override
                public void onError(String message) {

                }
            });
        }


        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            for (int i = 0; i < datastore.size(); i++) {
//                                if (datastore.get(i).getEmail().equalsIgnoreCase(email) && datastore.get(i).getPassword().equalsIgnoreCase(password)) {
//                                    Toast.makeText(getApplicationContext(), "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
//                                    Intent is = new Intent(getApplicationContext(), MainActivity.class);
//                                    startActivity(is);
//                                    break;
//                                } else {
//                                    Toast.makeText(getApplicationContext(), "Login Thất Bại", Toast.LENGTH_SHORT).show();
//                                }
//                            }
                            Log.d("vh", task.getResult().toString());
                            Intent is = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(is);
                        } else {
                            Toast.makeText(getApplicationContext(), "Login Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.loginBtn:
                login();
                break;
            case R.id.btnquenmatkhau:
                Intent iKhoiPhuc = new Intent(DangNhapActivity.this, QuenMatKhau.class);
                startActivity(iKhoiPhuc);
        }
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user !=null){
                Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                startActivity(intent);
            };
    }
}
