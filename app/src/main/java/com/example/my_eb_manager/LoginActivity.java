package com.example.my_eb_manager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    //Element element;
    //private String[] url = { "http://m.e-lib.sen.go.kr/index.php", "http://m.e-lib.sen.go.kr/member/login.php", "https://www.naver.com" };

    private TextInputLayout layoutID, layoutPW;
    private EditText txtID, txtPW;
    private String ID, PW;
    private Button nextBtn, preBtn;
    private TextView showLibText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtID = findViewById(R.id.textID);
        txtPW = findViewById(R.id.textPW);
        nextBtn = findViewById(R.id.nextBtn);
        preBtn = findViewById(R.id.preBtn);
        showLibText = findViewById(R.id.showLibText);

        // showLibText 세팅
        if (SaveSharedPreference.getLibrary(this).length() > 0) {
            showLibText.setText(SaveSharedPreference.getLibrary(this));
        }

        nextBtn.setOnClickListener(v -> {
            ID = txtID.getText().toString();
            PW = txtPW.getText().toString();

            if (ID.length() > 0 && PW.length() > 0) {
                SaveSharedPreference.setLoginInfo(this, ID, PW);    // ID, PW 저장
                Toast.makeText(this, "ID와 PW가 저장되었습니다.", Toast.LENGTH_SHORT).show();

                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);  // 화면 전환 애니메이션
                finish();
            } else {
                Toast.makeText(this, "ID나 PW가 비었어요!", Toast.LENGTH_SHORT).show();
                /*if (ID.length() <= 0)
                    layoutID.setError("empty!");
                if (PW.length() <= 0)
                    layoutPW.setError("empty!");*/
            }
        });

        preBtn.setOnClickListener(v -> {
            Intent libIntent = new Intent(getApplicationContext(), LibSelectActivity.class);
            startActivity(libIntent);
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
            finish();
        });
    }

    // JsoupAsyncTask 였던 것
    /*private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

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
                //elements = doc.getElementsByClass("logo_naver").get(0).getElementsByClass("blind");

                element = doc.getElementById("member_id_tmp");
                WebElement element_id = (WebElement) element;
                //WebElement element_pw = (WebElement) doc.getElementById("member_pw_tmp");
                //WebElement element_login_btn = (WebElement) doc.getElementById("save-btn");
                element_id.sendKeys(ID);
                //element_pw.sendKeys(PW);
                //element_pw.sendKeys(Keys.ENTER);
                Toast.makeText(LoginActivity.this, element_id.getText() + "로그인", Toast.LENGTH_SHORT).show();
                //Toast.makeText(LoginActivity.this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }*/
}
