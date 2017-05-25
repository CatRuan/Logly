package com.rd.logly;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rd.pflog.PFLog;
import com.rd.pflog.log.KeyMethod;
import com.rd.logly.app.MyPFInfoCollector;

import java.util.Map;
import java.util.TreeMap;


public class PerformanceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnLogin;
    private Button mBtnDownload;
    private Button mBtnIO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);
        initView();
    }

    private void initView() {
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mBtnDownload = (Button) findViewById(R.id.btnDownload);
        mBtnIO = (Button) findViewById(R.id.btnIO);

        mBtnLogin.setOnClickListener(this);
        mBtnDownload.setOnClickListener(this);
        mBtnIO.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                Login();
                break;
            case R.id.btnDownload:
                download();
                break;
            case R.id.btnIO:
                io();
                break;
        }

    }

    Map<String, Object> mPfInfos = new TreeMap<>();

    @KeyMethod
    private void download() {
        Log.i("pflog", "下载");
        /**
         * 网络请求……
         */
        String taskId = "0001";
        String userName = "ruand";
        /**
         *网络请求……
         */
        mPfInfos.put("taskId", taskId);
        mPfInfos.put("userName", userName);
        MyPFInfoCollector myPFInfoCollecter = new MyPFInfoCollector(mPfInfos);
        PFLog.getInstance().setPFInfoCollecter(myPFInfoCollecter);//添加额外信息

    }

    @KeyMethod
    private void Login() {
        Log.i("pflog", "登陆");

    }

    @KeyMethod
    private void io() {
        Log.i("pflog", "输入输出");
    }
}
