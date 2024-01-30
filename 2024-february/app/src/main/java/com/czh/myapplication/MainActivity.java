package com.czh.myapplication;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
    }

    private void bindViews() {
        textView1 = findViewById(R.id.text_view1);
        textView2 = findViewById(R.id.text_view2);
        textView3 = findViewById(R.id.text_view3);
    }

    public void onClickGet(View view) {
        Log.e("idcomcn", "onClick: 点击了确定");
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);

        long totalMemory = memoryInfo.totalMem;
        long availableMemory = memoryInfo.availMem;
        long usedMemory = totalMemory - availableMemory;

        textView1.setText("总内存大小：" + formatSize(totalMemory));
        textView2.setText("可用内存大小：" + formatSize(availableMemory));
        textView3.setText("已用内存大小：" + formatSize(usedMemory));
    }

    private String formatSize(long size) {
        String suffix = "B";
        double value = size;

        if (value >= 1024) {
            suffix = "KB";
            value /= 1024;
        }
        if (value >= 1024) {
            suffix = "MB";
            value /= 1024;
        }
        if (value >= 1024) {
            suffix = "GB";
            value /= 1024;
        }
        return String.format("%.2f%s", value, suffix);
    }
}
