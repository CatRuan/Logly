package com.rd.logly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rd.buglog.BugLog;

import java.io.File;

public class LogCheckActivity extends AppCompatActivity {

    private TextView mTvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_check);
        mTvLog = (TextView) findViewById(R.id.tvLog);
        final File file = BugLog.getInstance().getBugLogFile();

        BugLog.getInstance().getLogFileContent(file, new BugLog.OnCompleteTxtRead() {
            @Override
            public void setOnCompleteTxtRead(String result) {
                Log.i("buglog","路径：" + file.getAbsolutePath()+"\n" + result);
                mTvLog.setText("路径：" + file.getAbsolutePath() + "\n长度：" + result.length() + "\n" + result);
            }
        });
    }
}
