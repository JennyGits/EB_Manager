package com.example.my_eb_manager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MyAlarmActivity extends AppCompatActivity {

    private ImageButton backBtn, deleteBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_alarm);


        int position = getIntent().getIntExtra("position", -1);

        backBtn = findViewById(R.id.go_back);
        deleteBtn = findViewById(R.id.alarm_activity_delete);

        backBtn.setOnClickListener(v -> finish());

        deleteBtn.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.putExtra("position", position);
            startActivity(i);
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
            finish();
        });
    }
}
