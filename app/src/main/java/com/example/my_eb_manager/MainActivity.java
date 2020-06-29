package com.example.my_eb_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calendar_btn = findViewById(R.id.calendar);
        calendar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),CalendarActivity.class);
                startActivity(intent);
            }
        });

//Friebase Notification
//        FirebaseInstanceId.getInstance().getInstanceId()
//                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
//                        if(!task.isSuccessful()){
//                            Log.w("FCM Log","getInstanceId failed",task.getException());
//                            return;
//                        }
//                        String token = task.getResult().getToken();
//                        Log.d("FCM Log","FCM 토큰: "+ token);
//                        Toast.makeText(MainActivity.this,token,Toast.LENGTH_SHORT).show();
//                    }
//                });

        Button to_notipage = findViewById(R.id.to_notipage);
        to_notipage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),NotifigationActivity.class);
                startActivity(intent);
            }
        });

    }


}
