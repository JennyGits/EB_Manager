package com.example.my_eb_manager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private String[] url = { "https://www.naver.com" };
    Elements elements;
    private TextView textView;

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

        textView = findViewById(R.id.textView);

        btnLogin.setOnClickListener(v -> {
            ID = txtID.getText().toString();
            PW = txtPW.getText().toString();
            //SaveSharedPreference.setLoginInfo(LoginActivity.this, ID, PW);
            //Toast.makeText(this, "로그인 정보가 저장되었습니다.", Toast.LENGTH_SHORT).show();

            JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
            jsoupAsyncTask.execute();
            if (elements != null) {
                textView.setText(elements.text());
            }
        });

    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Connection.Response execute = null;
            try {
                execute = Jsoup.connect(url[0]).execute();
                Document doc = Jsoup.parse(execute.body());

                // "네이버"를 텍스트로 가진 span 가져옴
                elements = doc.getElementsByClass("logo_naver").get(0).getElementsByClass("blind");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
