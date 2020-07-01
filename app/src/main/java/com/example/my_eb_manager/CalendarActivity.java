package com.example.my_eb_manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {
    String date;
    String current_date;

    private static final String NOTIFIGATION_CHANNEL_ID = "1001";
    Calendar cal_date = Calendar.getInstance();

    String selected_date;
    NotificationManagerCompat notificationManager;
    NotificationCompat.Builder builder;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView calendarView = findViewById(R.id.calendarView);
        final Button reservation_btn = findViewById(R.id.reservation_btn);
        final Button currentdate = findViewById(R.id.currentdate);

        currentdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CalendarActivity.this, current_date, Toast.LENGTH_SHORT).show();
            }
        });

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = simpleDateFormat.format(mDate);
        current_date = simpleDateFormat.format(cal_date.getTime());


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                month = month + 1;
                //selected_date = year + "-" + month + "-" + day;
                selected_date = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);
                Toast.makeText(CalendarActivity.this, selected_date, Toast.LENGTH_SHORT).show();
                reservation_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        createNotification();
                        if(selected_date.equals(current_date)){
                            Toast.makeText(CalendarActivity.this,"asdf",Toast.LENGTH_SHORT).show();
                            Notification_notify();
                        }
                    }
                });
            }

        });
    }
    public void createNotification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NOTIFIGATION_CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }

        Intent intent = new Intent(this, Notifigation_ResultActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        builder = new NotificationCompat.Builder(this, NOTIFIGATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("대출 예약 만료 알림")
                .setContentText("책 대출 예약일이 만료되었습니다")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
    }

    public void Notification_notify() {
            //알림 호출
            notificationManager = NotificationManagerCompat.from(this);
            // notificationId 설정
            int notificationId = 256;
            //notificationId 전달
            notificationManager.notify(notificationId, builder.build());

    }
}