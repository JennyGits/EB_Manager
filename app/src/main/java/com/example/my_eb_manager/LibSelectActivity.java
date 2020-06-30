package com.example.my_eb_manager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LibSelectActivity extends AppCompatActivity {

    private ArrayList<String> libs = new ArrayList();
    private ArrayAdapter<String> arrayAdapter;

    private Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_select);

        spinner = findViewById(R.id.spinner);

        libs.add("서울특별시 교육청 전자도서관");
        libs.add("(추가 예정입니다.)");

        // ArrayAdapter에 ArrayList를 넣어줌
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, libs);

        spinner.setAdapter(arrayAdapter);
    }
}
