package com.czh.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
    }


    private void bindViews() {
        textView = findViewById(R.id.text_view);
    }

    public void onClickGet(View view) {
        Log.e("idcomcn", "onClick: 点击了确定");
        final OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url("https://www.publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example")
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    String string = response.body().string();
                    Log.d("idcomcn", "response=====" + string);
                    //idcomcn TODO
                    textView.setText(string.substring(0,10));
                    response.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
