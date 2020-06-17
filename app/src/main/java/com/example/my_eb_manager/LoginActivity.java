package com.example.my_eb_manager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

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

        btnLogin.setOnClickListener(v -> {
            ID = txtID.getText().toString();
            PW = txtPW.getText().toString();
            loginCheck(ID, PW);
        });
    }

    private void loginCheck(final String ID_, final String PW_) {
        class LoginAsync extends AsyncTask<String, Void, String> {
            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(LoginActivity.this, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... strings) {

                InputStream inputStream = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("username", strings[0]));
                nameValuePairs.add(new BasicNameValuePair("password", strings[1]));

                String result = null;

                try {
                    HttpClient httpClient = SessionControl.getHttpclient();
                    HttpPost httpPost = new HttpPost("https://nid.naver.com/nidlogin.login");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();

                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                String s1 = s.trim();
                loadingDialog.dismiss();
                if (s1.equalsIgnoreCase("OK")) {
                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                    SessionControl.cookies = SessionControl.httpClient.getCookieStore().getCookies();

                    if (!SessionControl.cookies.isEmpty()) {
                        Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
                        i.putExtra("myurl", "https://www.naver.com/");
                        startActivity(i);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                }
            }
        }

        new LoginAsync().execute(ID, PW);
    }
}
