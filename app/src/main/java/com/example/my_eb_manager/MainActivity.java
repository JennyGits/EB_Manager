package com.example.my_eb_manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton_add;

    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private ItemTouchHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton_add = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recycler1);

        // RecyclerView의 레이아웃 방식을 지정
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        // RecyclerView의 Adapter 세팅
        adapter = new ListAdapter();
        recyclerView.setAdapter(adapter);

        // ItemTouchHelper 생성
        helper = new ItemTouchHelper(new ItemTouchHelperCallback(new ItemTouchHelperListener() {
            @Override
            public boolean onItemMove(int from_position, int to_position) {
                // 이동할 객체 저장
                NotiItem notiItem = adapter.items.get(from_position);
                // 이동할 객체 삭제
                adapter.items.remove(from_position);
                // 이동하고 싶은 position에 추가
                adapter.items.add(to_position, notiItem);

                // Adapter에 데이터 이동알림
                adapter.notifyItemMoved(from_position, to_position);
                return true;
            }

            //-- 스와이프 --//
            @Override
            public void onItemSwipe(int position) {
                adapter.items.remove(position);
                adapter.notifyItemRemoved(position);
            }
        }));
        // RecyclerView 에 ItemTouchHelper 붙이기
        helper.attachToRecyclerView(recyclerView);

        // 추가 버튼 누름
        floatingActionButton_add.setOnClickListener(v -> {
            // 객체 생성
            NotiItem notiItem = new NotiItem("누가 내 머리에 똥 쌌어");

            // ListAdapter에 객체 추가
            adapter.addItem(notiItem);
            adapter.notifyDataSetChanged();
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
