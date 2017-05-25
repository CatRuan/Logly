package com.rd.logly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BugLogActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnANR;
    private Button mBtnException;
    private Button mBtnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_log);
        initView();
    }

    private void initView() {
        mBtnANR = (Button) findViewById(R.id.btnANR);
        mBtnException = (Button) findViewById(R.id.btnException);
        mBtnCheck = (Button) findViewById(R.id.btnCheck);
        mBtnANR.setOnClickListener(this);
        mBtnException.setOnClickListener(this);
        mBtnCheck.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnANR:
                getANR();
                break;
            case R.id.btnException:
                getException();
                break;
            case R.id.btnCheck:
                Intent intent = new Intent(this, LogCheckActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void getException() {
        TextView text = null;
        text.setText("log");
    }

    private void getANR() {
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
