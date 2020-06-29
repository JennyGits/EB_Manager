package com.example.my_eb_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton_add = findViewById(R.id.floatingActionButton);
        floatingActionButton_add.setOnClickListener(v -> {
            Toast.makeText(this, "버튼 눌림", Toast.LENGTH_SHORT).show();
        });
    }

    private void is_LoginInfo_Exist() {
        // 저장된 정보가 있는지 확인
        if (SaveSharedPreference.getUserName(MainActivity.this).length() <= 0) {
            // 로그인 액티비티 불러옴
            Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginIntent);
        } else {
            // 로그인 하기
            Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
        }

    }
}
