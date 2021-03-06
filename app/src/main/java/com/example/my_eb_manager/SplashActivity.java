package com.example.my_eb_manager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            // 로그인 정보가 저장되어 있는지
            if (SaveSharedPreference.getID(SplashActivity.this).length() <= 0) {
                // Call library Activity
                Intent libIntent = new Intent(getApplicationContext(), LibSelectActivity.class);
                startActivity(libIntent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);  // 화면 전환 애니메이션
                finish();
            } else {
                // Call Main Activity
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
                finish();
            }

        }, 1500);
    }
}
