package com.example.my_eb_manager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LibSelectActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] libs = new String[2];
    private ArrayAdapter<String> arrayAdapter;

    private Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_select);

        spinner = findViewById(R.id.spinner);

        // strings.xml 에서 불러오기
        libs = getResources().getStringArray(R.array.Lib_Array);

        // ArrayAdapter에 ArrayList를 넣어줌
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, libs);
        // spinner에 ArrayAdapter 추가
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                SaveSharedPreference.setLibrary(this, libs[0]);
                Toast.makeText(this, libs[0], Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, libs[1], Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
