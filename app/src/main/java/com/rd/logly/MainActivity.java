package com.rd.logly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnBugLog;
    private Button mBtnPerformLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnBugLog = (Button) findViewById(R.id.btnBugLog);
        mBtnPerformLog = (Button) findViewById(R.id.btnPerformLog);

        mBtnBugLog.setOnClickListener(this);
        mBtnPerformLog.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBugLog:
                Intent intent = new Intent(this, BugLogActivity.class);
                startActivity(intent);
                break;
            case R.id.btnPerformLog:
                Intent intent2 = new Intent(this, PerformanceActivity.class);
                startActivity(intent2);
                break;
        }

    }


}
