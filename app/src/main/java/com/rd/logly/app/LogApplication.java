package com.rd.logly.app;

import android.app.Application;

import com.rd.buglog.BugLog;
import com.rd.pflog.PFLog;

/**
 * Created by ruand on 2017/5/24.
 */

public class LogApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BugLog.getInstance().start(this);//开启奔溃日志
        PFLog.getInstance().start(this, 7);//初始化日志管理器


    }

}
