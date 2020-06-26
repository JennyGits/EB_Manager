package com.example.my_eb_manager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText txtID, txtPW;
    private String ID, PW;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtID = findViewById(R.id.textID);
        txtPW = findViewById(R.id.textPW);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            ID = txtID.getText().toString();
            PW = txtPW.getText().toString();
            SaveSharedPreference.setLoginInfo(LoginActivity.this, ID, PW);
            Toast.makeText(this, "로그인 정보가 저장되었습니다.", Toast.LENGTH_SHORT).show();
        });
    }
}
